package com.tatvacoconet.controller;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.MasterDocumentDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;
import com.tatvacoconet.service.DocumentDataService;
import com.tatvacoconet.service.DocumentLinkService;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by pca48 on 5/2/2017.
 */

    @RestController
/* @RequestMapping("/document") */
    public class DocumentDataController {
        final static Logger logger = Logger.getLogger(DocumentDataController.class);
        @Autowired
        DocumentDataService dataService;

        @Autowired
        DocumentLinkService linkService;

	/*@Autowired
	ServletContext servletContext;*/

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");


/*
    @RequestMapping(value = "/documentList", method = RequestMethod.GET)
    public ModelAndView displayDocumentList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("DocumentList");
        return mav;
    }

*/


	/*                      List all Method 					*/

        @RequestMapping(value = "/documentList", method = RequestMethod.GET, headers = "Accept=application/json")
        public ResponseEntity<List<DocumentDTO>> getAllDocuments(){
            // Retrieve all documents
            List<DocumentData> document = dataService.getAllDocs();

            List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();

            for (DocumentData doc : document) {
                DocumentDTO dto = new DocumentDTO();
                dto.setDocumentData(doc);
                dto.setDocumentLink(linkService.getAllDocumentsLink(doc.getDocumentId()));

                documentDTO.add(dto);
            }

            // List<Document_Data> document = dataService.getAllDocs();
            if (documentDTO.isEmpty()) {
                logger.debug("document does not exists");
                return new ResponseEntity<List<DocumentDTO>>(HttpStatus.NO_CONTENT);
            } else {
                logger.debug("Found " + documentDTO.size() + " document");
                logger.debug(Arrays.toString(documentDTO.toArray()));
                return new ResponseEntity<List<DocumentDTO>>(documentDTO,
                        HttpStatus.OK);
            }
        }

    @RequestMapping(value = "/adddocument", method = RequestMethod.GET)
    public ModelAndView displayAddDocument() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("AddDocument");
        return mav;
    }


	/* 							   Add Method 							      	*/

    @RequestMapping(value = "/AddDocument", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<MasterDocumentDTO> addDocument(@RequestBody MasterDocumentDTO masterDocumentDTO1,
                                                         @ModelAttribute DocumentData document,
                                                         @ModelAttribute DocumentLink document_link,
                                                         HttpSession session, Model model){
        // public String postAdd(@ModelAttribute("documentAttribute") Document_Data document,HttpSession session) {
        logger.debug("Received request to add new record");
        DocumentData document2 = (DocumentData) session.getAttribute("document1");
        if (document2 != null){
            document.setFileSize(document2.getFileSize());
            document.setFilePath(document2.getFilePath());
            document.setDocumentDescription(document2.getDocumentDescription());
            document.setDocumentName(document2.getDocumentName());
        }


        DocumentData document1=new DocumentData();

        document1=document;
        document1.setCreationDate(masterDocumentDTO1.getCreationDate());
        document1.setValidFrom(masterDocumentDTO1.getValidFrom());
        document1.setValidTo(masterDocumentDTO1.getValidTo());
        document1.setDocumentStatus(masterDocumentDTO1.getDocumentStatus());
        document1.setDocumentTag(masterDocumentDTO1.getDocumentTag());
        document1.setDocumentType(masterDocumentDTO1.getDocumentType());
        document1.setVerticalData(masterDocumentDTO1.getVerticalData());

        if(document1!=null)
        {
            dataService.RegisterOrUpdate(document1);
            session.removeAttribute("document1");
        }

        DocumentLink documentLink1=new DocumentLink();

        documentLink1.setFirstName(masterDocumentDTO1.getFirstName());
        documentLink1.setLastName(masterDocumentDTO1.getLastName());
        documentLink1.setGroupDetails(masterDocumentDTO1.getGroupDetails());
        documentLink1.setRoleDetails(masterDocumentDTO1.getRoleDetails());
        documentLink1.setUserId(masterDocumentDTO1.getUserId());

        if(documentLink1!=null)
        {	documentLink1.setDocumentData(document1);
            linkService.addDocument(documentLink1);

        }
        logger.debug("Added:: " + document);
        return new ResponseEntity<MasterDocumentDTO>(masterDocumentDTO1, HttpStatus.CREATED);
    }

	/* 							Add File Method 						 	*/

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @Produces("application/json")
    @Consumes("application/pdf")
    public ResponseEntity<DocumentData> uploadDocument(@RequestParam("file") MultipartFile multipartFile,HttpSession session){
	/* @RequestBody Document_Data document */
        DocumentData document1 = new DocumentData();
		/* null the session */
        session.removeAttribute("document1");
		/* Fill the values into session */
        if (!multipartFile.isEmpty()) {
            try {
                document1.setDocumentName(multipartFile.getOriginalFilename());
                document1.setDocumentDescription(multipartFile.getContentType());
                document1.setFileSize(multipartFile.getSize());
                document1.setFilePath(multipartFile.getBytes());

                session.setAttribute("document1", document1);

                logger.debug("Added:: " + document1);

                return new ResponseEntity<DocumentData>(document1,HttpStatus.CREATED);
            }
            catch (Exception e){
                return new ResponseEntity<DocumentData>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<DocumentData>(HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Retrieves the "Edit Existing Record" page
     */
    @RequestMapping(value = "/documentListByID/{documentId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> getDocumentsByID(@PathVariable("documentId")long documentId){
        // Retrieve all documents by  id

        DocumentDTO documentDTO=new DocumentDTO();
        documentDTO.setDocumentData(dataService.getDocumentByID(documentId));
        documentDTO.setDocumentLink(linkService.getAllDocumentsLink(documentId));
        logger.debug("Added:: " + documentDTO);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);

    }
    @RequestMapping(value="/updateDocument", method = RequestMethod.PUT, headers="Accept=application/json")
    public void updateDocument(@RequestBody MasterDocumentDTO masterDocumentDTO1, @ModelAttribute DocumentData document,@ModelAttribute DocumentLink documentLink)
    {
        DocumentDTO documentDTO=new DocumentDTO();
        documentDTO.setDocumentData(dataService.getDocumentByID(masterDocumentDTO1.getDocumentId()));

        document=documentDTO.getDocumentData();

        document.setDocumentId(masterDocumentDTO1.getDocumentId());
        document.setDocumentDescription(masterDocumentDTO1.getDocumentDescription());
        document.setDocumentName(masterDocumentDTO1.getDocumentName());
        document.setDocumentStatus(masterDocumentDTO1.getDocumentStatus());
        document.setDocumentTag(masterDocumentDTO1.getDocumentTag());
        document.setCreationDate(masterDocumentDTO1.getCreationDate());
        document.setValidFrom(masterDocumentDTO1.getValidFrom());
        document.setValidTo(masterDocumentDTO1.getValidTo());
        document.setDocumentType(masterDocumentDTO1.getDocumentType());
        document.setVerticalData(masterDocumentDTO1.getVerticalData());

        if(document!=null)
        {	dataService.updateDocument(document);

        }
        documentLink.setDocumentLinkId(masterDocumentDTO1.getDocumentLinkId());
        documentLink.setFirstName(masterDocumentDTO1.getFirstName());
        documentLink.setLastName(masterDocumentDTO1.getLastName());
        documentLink.setGroupDetails(masterDocumentDTO1.getGroupDetails());
        documentLink.setRoleDetails(masterDocumentDTO1.getRoleDetails());
        documentLink.setUserId(masterDocumentDTO1.getUserId());
        documentLink.setDocumentData(document);
        if(documentLink!=null)
        {
            //document_link.setDocument_data(document);

            linkService.updateDocumentLink(documentLink);
        }
    }
    @RequestMapping(value = "/deleteDocument/{documentId}", method = RequestMethod.GET)
    public ResponseEntity<DocumentData> getDelete(@PathVariable("documentId")long documentId) {
        logger.debug("Received request to delete record");

        // Delete all associated credit cards first
        linkService.deleteAll(documentId);

        // Delete person
        dataService.delete(documentId);

        // Redirect to url
        return new ResponseEntity<DocumentData>(HttpStatus.NO_CONTENT);

    }
    }

