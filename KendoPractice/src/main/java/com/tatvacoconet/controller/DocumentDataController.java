package com.tatvacoconet.controller;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;
import com.tatvacoconet.service.DocumentDataService;
import com.tatvacoconet.service.DocumentLinkService;

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


	/*                      List all Method 					*/

        @RequestMapping(value = "/documentList", method = RequestMethod.GET, headers = "Accept=application/json")
        public ResponseEntity<List<DocumentDTO>> getAllDocuments(Model model){
            // Retrieve all documents
            List<DocumentData> document = dataService.getAllDocs();

            // Prepare model object
            List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();

            for (DocumentData doc : document) {
                // Create new data transfer object
                DocumentDTO dto = new DocumentDTO();
                dto.setDocumentId(doc.getDocumentId());
                dto.setDocumentName(doc.getDocumentName());
                dto.setDocumentDescription(doc.getDocumentDescription());
                dto.setFilePath(doc.getFilePath());
                dto.setFileSize(doc.getFileSize());
                dto.setDocumentStatus(doc.getDocumentStatus());
                dto.setDocumentTag(doc.getDocumentTag());
                dto.setDocumentType(doc.getDocumentType());
                dto.setCreationDate(doc.getCreationDate());
                dto.setValidFrom(doc.getValidFrom());
                dto.setValidTo(doc.getValidTo());
                dto.setVerticalData(doc.getVerticalData());
                dto.setDocumentLink(linkService.getAllDocumentsLink(doc
                        .getDocumentId()));
                // Add to model list
                documentDTO.add(dto);
            }

            // List<DocumentData> document = dataService.getAllDocs();
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
	/* 							   Add Method 							      	*/

        @RequestMapping(value = "/addDocument", method = RequestMethod.POST, headers = "Accept=application/json")
        public ResponseEntity<DocumentData> addDocument(@RequestBody DocumentData document,
                                                        @ModelAttribute DocumentLink documentlink1,
                                                        HttpSession session){
            // public String postAdd(@ModelAttribute("documentAttribute") DocumentData document,HttpSession session) {
            logger.debug("Received request to add new record");
            session.removeAttribute("documentid");

            DocumentData document1 = (DocumentData) session.getAttribute("document1");
            if (document1 != null){
                document.setFileSize(document1.getFileSize());
                document.setFilePath(document1.getFilePath());
                document.setDocumentDescription(document1.getDocumentDescription());
                document.setDocumentName(document1.getDocumentName());
            }

            // Delegate to service
            dataService.RegisterOrUpdate(document);

            DocumentData doc = document;

            documentlink1.setDocumentData(doc);

            DocumentLink documentlink2 = documentlink1;
            session.setAttribute("documentid", documentlink2);

            session.removeAttribute("document1");

            logger.debug("Added:: " + document);
            return new ResponseEntity<DocumentData>(document, HttpStatus.CREATED);
        }

	/* 							Add File Method 						 	*/

        @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
        @Produces("application/json")
        @Consumes("application/pdf")
        public ResponseEntity<DocumentData> uploadDocument(@RequestParam("file")
                                                                   MultipartFile multipartFile, HttpSession session){
	/* @RequestBody Document_Data document */
            DocumentData document1 = new DocumentData();


		/* null the session */
            session.removeAttribute("document1");

		/* Fill the values into session */

            if (!multipartFile.isEmpty()) {
                try {
				/*String originalFilename = multipartFile.getOriginalFilename();
				 File destinationFile = new File(
				  servletContext.getRealPath("/resources/files/") +
				  originalFilename); multipartFile.transferTo(destinationFile);
				 */
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

    }

