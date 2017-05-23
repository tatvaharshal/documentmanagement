package com.tatvacoconet.controller;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.DocumentLinkDTO;
import com.tatvacoconet.dto.DocumentMapper;
import com.tatvacoconet.entity.AddressScope;
import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;
import com.tatvacoconet.entity.VerticalData;
import com.tatvacoconet.service.DocumentDataService;
import com.tatvacoconet.service.DocumentLinkService;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

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

    @RequestMapping(value = "/AddDocument", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> addDocument(@RequestBody @Valid DocumentDTO documentDTO,
                                                   @ModelAttribute DocumentData document,
                                                   @ModelAttribute DocumentLink documentLink,
                                                   Errors errors,Model model,
                                                   HttpServletRequest request) throws ParseException{

        // public String postAdd(@ModelAttribute("documentAttribute") Document_Data document,HttpSession session) {
        logger.debug("Received request to add new record");

        documentValidator.validate(documentDTO, errors);
        if (errors.hasErrors()) {
            dataService.delete(documentDTO.getDocumentId());
        }

        else {
           document=dataService.getDocumentByID(documentDTO.getDocumentId());
            document.setCreationDate(documentDTO.getCreationDate());
            //document2.setImportDate(document.getImportDate());
         /*   document.setDocumentDescription(documentDTO.getDocumentDescription());
            document.setDocumentName(documentDTO.getDocumentName());*/
            document.setImportDate(documentDTO.getCreationDate());
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setDocumentTag(documentDTO.getDocumentTag());
            document.setDocumentType(documentDTO.getDocumentType());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            document.setAddressScope(documentDTO.getAddressScope());
            document.setVerticalData(VerticalData.BOI);
            dataService.updateDocument(document);

        }

        List<DocumentLinkDTO> documentlinkdto = documentDTO.getDocumentLinkDTO();
        for (DocumentLinkDTO documentLinkDTO : documentlinkdto) {
            documentLinkValidator.validate(documentLinkDTO, errors);
            if(errors.hasErrors()){
                dataService.delete(document.getDocumentId());
            }

            documentLinkDTO.setDocumentData(document);
            documentLink=documentMapper.convertToDocumentLinkEntity(documentLinkDTO);
            linkService.addDocument(documentLink);
        }
        logger.debug("Added:: " + document);
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }
/* 							Add File Method 						 	*/

    @RequestMapping(value = "/fileUpload", headers="content-type=multipart/*", method = RequestMethod.POST)
    public ResponseEntity<DocumentDTO> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {

        DocumentDTO documentDTO = new DocumentDTO();
        DocumentDTO documentFileDTO = new DocumentDTO();
        DocumentData document = new DocumentData();

        //MultipartFile multipartFile = file.getFile();
        if (!file.isEmpty()) {
            Random r = new Random();
            int random = r.nextInt(50);
            documentDTO.setDocumentName(random+"-"+file.getOriginalFilename());
            documentDTO.setDocumentDescription(file.getContentType());
            documentDTO.setFileSize(file.getSize() / 1024);
            documentDTO.setFilePath(file.getBytes());

			/*documentValidator.validateFileDetails(documentDTO, result);
			if (result.hasErrors()) {
				System.out.println(result);
				//return new ResponseEntity<DocumentDTO>(documentDTO1,HttpStatus.BAD_REQUEST);
			}*/
            document = documentMapper.convertToDocumentEntity(documentDTO);
            DocumentData documentUpload = dataService.RegisterOrUpdate(document);
            documentFileDTO = documentMapper.convertToDocumentDto(documentUpload);
        }
        return new ResponseEntity<DocumentDTO>(documentFileDTO, HttpStatus.CREATED);
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

        String filename=document.getDocumentName();
        String fileName=filename.substring(filename.lastIndexOf("-") + 1);
        //documentDTO=documentMapper.convertToDocumentDto(document);
        // documentDTO.getFile_path();
        ServletOutputStream outputStream = response.getOutputStream();
        ServletContext sc = request.getSession().getServletContext();
        //String mimetype = sc.getMimeType(documentDTO.getDocument_description());
        //response.setContentType(mimetype);
        //response.setContentLengthLong(documentDTO.getFile_size());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        outputStream.write(document.getFilePath());
        outputStream.flush();
        outputStream.close();
    }
    @RequestMapping(value = "/updateDocument", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> updateDocument(@RequestBody @Valid DocumentDTO documentDTO,
                                                      @ModelAttribute DocumentData document,
                                                      @ModelAttribute DocumentLink documentLink, Errors errors) {

        //DocumentDTO documentDTO1=new DocumentDTO();
        documentValidator.validate(documentDTO, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
        }

       // document = documentMapper.convertToDocumentEntity(documentDTO);
         document=dataService.getDocumentByID(documentDTO.getDocumentId());
        if(document.getAddressScope()== AddressScope.None)
        {
            document.setCreationDate(documentDTO.getCreationDate());
            document.setImportDate(documentDTO.getImportDate());
            document.setDocumentDescription(documentDTO.getDocumentDescription());
            document.setDocumentName(documentDTO.getDocumentName());
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setDocumentTag(documentDTO.getDocumentTag());
            document.setDocumentType(documentDTO.getDocumentType());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            document.setAddressScope(documentDTO.getAddressScope());
            document.setVerticalData(VerticalData.BOI);
            dataService.updateDocument(document);

            //List<Document_Link> documentLink=linkService.getAllDocumentsLink(document.getDocument_id());

            DocumentLink documentLink1=linkService.getAllDocumentsById(document.getDocumentId());
            List<DocumentLinkDTO> documentlinkdto=documentDTO.getDocumentLinkDTO();

            List<DocumentLinkDTO> linkDTO = new ArrayList<DocumentLinkDTO>();
            for (DocumentLinkDTO documentLinkDTO : documentlinkdto) {

                documentLinkValidator.validate(documentLinkDTO, errors);
                if(errors.hasErrors()){
                    return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.BAD_REQUEST);
                }

                documentLink=documentMapper.convertToDocumentLinkEntity(documentLinkDTO);
                documentLink1.setGroupDetails(documentLink.getGroupDetails());
                documentLink1.setRoleDetails(documentLink.getRoleDetails());
                documentLink1.setUserId(documentLink.getUserId());
                linkService.addDocument(documentLink1);


                documentLinkDTO=documentMapper.convertToDocumentLinkDto(documentLink1);

                linkDTO.add(documentLinkDTO);
                documentDTO.setDocumentLinkDTO(linkDTO);

            }
        }

        else
        {
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            document.setDocumentDescription(documentDTO.getDocumentDescription());
            document.setDocumentTag(documentDTO.getDocumentTag());
            dataService.updateDocument(document);

        }


        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/deleteDocument/{documentId}", method = RequestMethod.GET)
    public ResponseEntity<DocumentDTO> getDelete(@PathVariable("documentId") long documentId) {

        if (documentId=='\0') {
            return new ResponseEntity<DocumentDTO>(HttpStatus.BAD_REQUEST);
        }
        linkService.deleteAll(documentId);
        dataService.delete(documentId);

        return new ResponseEntity<DocumentDTO>(HttpStatus.NO_CONTENT);
    }
    }
