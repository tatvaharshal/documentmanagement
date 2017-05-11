package com.tatvacoconet.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.DocumentLinkDTO;
import com.tatvacoconet.dto.DocumentMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    DocumentMapper documentMapper;
	/*@Autowired
	ServletContext servletContext;*/

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");



    @RequestMapping(value = "/DocumentList", method = RequestMethod.GET)
    public ModelAndView displayDocumentList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("DocumentList");
        return mav;
    }

	/*                      List all Method 					*/

        @RequestMapping(value = "/documentList", method = RequestMethod.GET, headers = "Accept=application/json")
        public ResponseEntity<List<DocumentDTO>> getAllDocuments(){

            List<DocumentData> document = dataService.getAllDocs();

            //document.get(0).getDocument_id();
            List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();

            for (DocumentData doc : document) {
                DocumentDTO dto = documentMapper.convertToDocumentDto(doc);

                List<DocumentLinkDTO> documentLinkDTO = new ArrayList<DocumentLinkDTO>();
                List<DocumentLink> documentlist = linkService.getAllDocumentsLink(doc.getDocumentId());

                for (DocumentLink doclist : documentlist) {
                    DocumentLinkDTO documentlinkdto=documentMapper.convertToDocumentLinkDto(doclist);
                    documentLinkDTO.add(documentlinkdto);
                }
                dto.setDocumentLinkDTO(documentLinkDTO);
                documentDTO.add(dto);

            }


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

    @RequestMapping(value = "/DocumentAdd", method = RequestMethod.GET)
    public ModelAndView displayAddDocument() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("DocumentAdd");
        return mav;
    }

    @RequestMapping(value = "/DocumentAdd", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> addDocument(@RequestBody @Valid DocumentDTO documentDTO,
                                                   @ModelAttribute DocumentData document,
                                                   @ModelAttribute DocumentLink documentLink,
                                                   BindingResult result, HttpSession session, Model model, HttpServletRequest request){
        // public String postAdd(@ModelAttribute("documentAttribute") Document_Data document,HttpSession session) {
        logger.debug("Received request to add new record");
        //Document_Data document2=new Document_Data();
        //DocumentDTO documentDTO1=new DocumentDTO();

        document = documentMapper.convertToDocumentEntity(documentDTO);
        long documentId=document.getDocumentId();

        if(document!=null)
        {
            dataService.updateDocument(document);
        }
        else
        {	dataService.delete(documentId);}

        List<DocumentLinkDTO> documentlinkdto=documentDTO.getDocumentLinkDTO();
        for (DocumentLinkDTO doclist : documentlinkdto) {
            doclist.setDocumentData(document);
            documentLink=documentMapper.convertToDocumentLinkEntity(doclist);
            linkService.addDocument(documentLink);

        }

        logger.debug("Added:: " + document);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }

/* 							Add File Method 						 	*/

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @Produces("application/json")
    @Consumes("application/pdf")

    public ResponseEntity<DocumentDTO> uploadDocument(@RequestPart("file")MultipartFile multipartFile,HttpSession session,HttpServletResponse response) throws IOException{

        DocumentDTO documentDTO =new DocumentDTO();

        DocumentDTO documentDTO1 =new DocumentDTO();

        DocumentData document=new DocumentData();
		/* null the session */

        response.setContentType("application/pdf");

		/* Fill the values into session */
        if (!multipartFile.isEmpty()) {

            documentDTO.setDocumentName(multipartFile.getOriginalFilename());
            documentDTO.setDocumentDescription(multipartFile.getContentType());
            documentDTO.setFileSize(multipartFile.getSize() / 1024);
            documentDTO.setFilePath(multipartFile.getBytes());
            document = documentMapper.convertToDocumentEntity(documentDTO);

            DocumentData document2=dataService.RegisterOrUpdate(document);

            documentDTO1=documentMapper.convertToDocumentDto(document2);


        }
        return new ResponseEntity<DocumentDTO>(documentDTO1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/documentListByID/{documentId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> getDocumentsByID(@PathVariable("documentId") long documentId){
        // Retrieve all documents by  id

        DocumentDTO documentDTO =new DocumentDTO();

        DocumentData document=dataService.getDocumentByID(documentId);
        documentDTO=documentMapper.convertToDocumentDto(document);

        List<DocumentLink> documentLink=linkService.getAllDocumentsLink(documentId);

        List<DocumentLinkDTO> documentLinkDTO = new ArrayList<DocumentLinkDTO>();
        for (DocumentLink doclist : documentLink) {

            DocumentLinkDTO documentlinkdto=documentMapper.convertToDocumentLinkDto(doclist);
            documentLinkDTO.add(documentlinkdto);
        }

        documentDTO.setDocumentLinkDTO(documentLinkDTO);


        return new ResponseEntity<DocumentDTO>(documentDTO,HttpStatus.OK);

    }

    @RequestMapping(value="/updateDocument", method = RequestMethod.PUT, headers="Accept=application/json")
    public ResponseEntity<DocumentDTO> updateDocument(@RequestBody @Valid DocumentDTO documentDTO,@ModelAttribute DocumentData document,@ModelAttribute DocumentLink documentLink,BindingResult result)
    {

        //DocumentDTO documentDTO1=new DocumentDTO();

        document = documentMapper.convertToDocumentEntity(documentDTO);

        DocumentData document2=dataService.getDocumentByID(document.getDocumentId());
        documentDTO=documentMapper.convertToDocumentDto(document2);

        dataService.updateDocument(document);

        List<DocumentLink> documentLink1=linkService.getAllDocumentsLink(document.getDocumentId());


        if(documentLink1==null)
        {

            List<DocumentLinkDTO> documentlinkdto=documentDTO.getDocumentLinkDTO();
            //documentlinkdto.get(0).setDocument_link_id(document_id);

            for (DocumentLinkDTO doclist : documentlinkdto) {
                documentLink=documentMapper.convertToDocumentLinkEntity(doclist);
                linkService.addDocument(documentLink);

            }
        }

        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/deleteDocument/{document_id}", method = RequestMethod.GET)
    public ResponseEntity<DocumentDTO> getDelete(@Valid @PathVariable("documentId")long documentId,BindingResult result) {
        logger.debug("Received request to delete record");

            linkService.deleteAll(documentId);

            dataService.delete(documentId);

        return new ResponseEntity<DocumentDTO>(HttpStatus.NO_CONTENT);
    }
}


