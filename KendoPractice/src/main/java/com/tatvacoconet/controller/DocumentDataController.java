package com.tatvacoconet.controller;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    public class DocumentDataController {
    final static Logger logger = Logger.getLogger(DocumentDataController.class);
    @Autowired
    DocumentDataService dataService;

    @Autowired
    DocumentLinkService linkService;

    @Autowired
    DocumentValidator documentValidator;

    @Autowired
    DocumentLinkValidator documentLinkValidator;

    @Autowired
    DocumentMapper documentMapper;

    boolean error = false;

    final int permitedSize = 5242880;  //~ 5 MB in bytes
    Date date = new Date();

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
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {

        List<DocumentData> document = dataService.getAllDocs();

        //document.get(0).getDocument_id();
        List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();

        for (DocumentData doc : document) {
            DocumentDTO dto = documentMapper.convertToDocumentDto(doc);

            List<DocumentLinkDTO> documentLinkDTO = new ArrayList<DocumentLinkDTO>();
            List<DocumentLink> documentlist = linkService.getAllDocumentsLink(doc.getDocumentId());

            for (DocumentLink doclist : documentlist) {
                DocumentLinkDTO documentlinkdto = documentMapper.convertToDocumentLinkDto(doclist);
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
                                                   BindingResult result, HttpSession session, Model model,
                                                   HttpServletRequest request) {

        // public String postAdd(@ModelAttribute("documentAttribute") Document_Data document,HttpSession session) {
        logger.debug("Received request to add new record");
        documentValidator.validate(documentDTO, result);
        if (result.hasErrors()) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
        }
        document = documentMapper.convertToDocumentEntity(documentDTO);

        DocumentData document2=dataService.getDocumentByID(document.getDocumentId());


        long documentId = document.getDocumentId();

        if (document != null) {
            document2.setCreationDate(document.getCreationDate());
            document2.setImportDate(document.getImportDate());
            document2.setDocumentDescription(document.getDocumentDescription());
            document2.setDocumentName(document.getDocumentName());
            document2.setDocumentStatus(document.getDocumentStatus());
            document2.setDocumentTag(document.getDocumentTag());
            document2.setDocumentType(document.getDocumentType());
            document2.setValidFrom(document.getValidFrom());
            document2.setValidTo(document.getValidTo());
            document2.setAddressScope(document.getAddressScope());
            dataService.updateDocument(document2);

        } else {
            dataService.delete(documentId);
        }

        List<DocumentLinkDTO> documentlinkdto = documentDTO.getDocumentLinkDTO();

        documentLinkValidator.validate(documentlinkdto, result);
        if (result.hasErrors()) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
        }

        for (DocumentLinkDTO doclist : documentlinkdto) {
            doclist.setDocumentData(document);
            documentLink = documentMapper.convertToDocumentLinkEntity(doclist);
            linkService.addDocument(documentLink);
        }
        logger.debug("Added:: " + document);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }
/* 							Add File Method 						 	*/

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @Produces("application/json")
    @Consumes("application/pdf")

    public ResponseEntity<DocumentDTO> uploadDocument(@RequestPart("file") MultipartFile multipartFile, BindingResult result,
                                                      HttpSession session, HttpServletResponse response) throws IOException {

        DocumentDTO documentDTO = new DocumentDTO();

        DocumentDTO documentDTO1 = new DocumentDTO();

        DocumentData document = new DocumentData();
		/* null the session */



		/* Fill the values into session */

        if (!multipartFile.isEmpty()) {
            try {
                if (multipartFile.getOriginalFilename() == null) {
                    result.rejectValue("documentName", "error.documentName");
                    error = true;
                } else {
                    documentDTO.setDocumentName(multipartFile.getOriginalFilename());
                }
                response.setContentType("application/pdf");
                String type = multipartFile.getContentType();
                if ((type.equals("application/pdf"))) {
                    documentDTO.setDocumentDescription(multipartFile.getContentType());
                } else {
                    result.rejectValue("documentDescription", "error.documentDescription");
                    error = true;
                }

                long filesize = multipartFile.getSize() / 1024;

                if (filesize < permitedSize) {
                    documentDTO.setFileSize(multipartFile.getSize() / 1024);
                } else {
                    result.rejectValue("fileSize", "error.fileSize");
                    error = true;
                }
                documentDTO.setFilePath(multipartFile.getBytes());

                documentValidator.validate(documentDTO, result);
                if (result.hasErrors()) {
                    return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
                }

                document = documentMapper.convertToDocumentEntity(documentDTO);
                DocumentData document2 = dataService.RegisterOrUpdate(document);
                documentDTO1 = documentMapper.convertToDocumentDto(document2);

            } catch (Exception e) {
                return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<DocumentDTO>(documentDTO1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/documentListByID/{documentId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> getDocumentsByID(@PathVariable("documentId") long documentId) {
        // Retrieve all documents by  id

        DocumentDTO documentDTO = new DocumentDTO();
        DocumentData document = dataService.getDocumentByID(documentId);
        documentDTO = documentMapper.convertToDocumentDto(document);

        List<DocumentLink> documentLink = linkService.getAllDocumentsLink(documentId);

        List<DocumentLinkDTO> documentLinkDTO = new ArrayList<DocumentLinkDTO>();
        for (DocumentLink doclist : documentLink) {

            DocumentLinkDTO documentlinkdto = documentMapper.convertToDocumentLinkDto(doclist);
            documentLinkDTO.add(documentlinkdto);
        }
        documentDTO.setDocumentLinkDTO(documentLinkDTO);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.OK);

    }


    @RequestMapping(value = "/download/{documentId}", method = RequestMethod.GET)
    public void download(@PathVariable("documentId") int documentId,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //DocumentDTO documentDTO =new DocumentDTO();
        DocumentData document=dataService.getDocumentByID(documentId);
        //documentDTO=documentMapper.convertToDocumentDto(document);
        // documentDTO.getFile_path();
        ServletOutputStream outputStream = response.getOutputStream();
        ServletContext sc = request.getSession().getServletContext();
        //String mimetype = sc.getMimeType(documentDTO.getDocument_description());
        //response.setContentType(mimetype);
        //response.setContentLengthLong(documentDTO.getFile_size());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getDocumentName() + "\"");
        outputStream.write(document.getFilePath());
        outputStream.flush();
        outputStream.close();
    }
    @RequestMapping(value = "/updateDocument", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> updateDocument(@RequestBody @Valid DocumentDTO documentDTO,
                                                      @ModelAttribute DocumentData document,
                                                      @ModelAttribute DocumentLink documentLink, BindingResult result) {

        //DocumentDTO documentDTO1=new DocumentDTO();
        documentValidator.validate(documentDTO, result);
        if (result.hasErrors()) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
        }

        document = documentMapper.convertToDocumentEntity(documentDTO);
        DocumentData document2=dataService.getDocumentByID(document.getDocumentId());
        if(document2.getAddressScope()==null)
        {
            document2.setCreationDate(document.getCreationDate());
            document2.setImportDate(document.getImportDate());
            document2.setDocumentDescription(document.getDocumentDescription());
            document2.setDocumentName(document.getDocumentName());
            document2.setDocumentStatus(document.getDocumentStatus());
            document2.setDocumentTag(document.getDocumentTag());
            document2.setDocumentType(document.getDocumentType());
            document2.setValidFrom(document.getValidFrom());
            document2.setValidTo(document.getValidTo());
            document2.setAddressScope(document.getAddressScope());
            dataService.updateDocument(document2);

            //List<Document_Link> documentLink=linkService.getAllDocumentsLink(document.getDocument_id());

            DocumentLink documentLink1=linkService.getAllDocumentsById(document.getDocumentId());
            List<DocumentLinkDTO> documentlinkdto=documentDTO.getDocumentLinkDTO();

            List<DocumentLinkDTO> documentLinkDTO = new ArrayList<DocumentLinkDTO>();
            for (DocumentLinkDTO doclist : documentlinkdto) {

                documentLink=documentMapper.convertToDocumentLinkEntity(doclist);
                documentLink1.setGroupDetails(documentLink.getGroupDetails());
                documentLink1.setRoleDetails(documentLink.getRoleDetails());
                documentLink1.setUserId(documentLink.getUserId());
                linkService.addDocument(documentLink1);


                doclist=documentMapper.convertToDocumentLinkDto(documentLink1);

                documentLinkDTO.add(doclist);
                documentDTO.setDocumentLinkDTO(documentLinkDTO);

            }
        }

        else
        {
            document2.setDocumentStatus(document.getDocumentStatus());
            document2.setValidFrom(document.getValidFrom());
            document2.setValidTo(document.getValidTo());
            document2.setDocumentDescription(document.getDocumentDescription());
            document2.setDocumentTag(document.getDocumentTag());
            dataService.updateDocument(document2);

        }


        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/deleteDocument/{documentId}", method = RequestMethod.GET)
    public ResponseEntity<DocumentDTO> getDelete(@Valid @PathVariable("documentId") long documentId, BindingResult result) {
        logger.debug("Received request to delete record");

        if (documentId == '\0') {
            result.rejectValue("documentId", "error.documentId");
            error = true;
        } else {
            linkService.deleteAll(documentId);

            dataService.delete(documentId);
        }
            return new ResponseEntity<DocumentDTO>(HttpStatus.NO_CONTENT);
        }
    }
