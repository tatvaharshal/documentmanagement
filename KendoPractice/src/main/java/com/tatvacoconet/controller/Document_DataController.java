package com.tatvacoconet.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
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

import com.tatvacoconet.entity.DocumentDTO;
import com.tatvacoconet.entity.Document_Data;
import com.tatvacoconet.entity.Document_Link;
import com.tatvacoconet.service.Document_DataService;
import com.tatvacoconet.service.Document_LinkService;

@RestController
/* @RequestMapping("/document") */
public class Document_DataController {
    final static Logger logger = Logger.getLogger(Document_DataController.class);
    @Autowired
    Document_DataService dataService;

    @Autowired
    Document_LinkService linkService;

    @Autowired
    ServletContext servletContext;

    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");


	/*                      List all Method 					*/

    @RequestMapping(value = "/documentList", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(Model model){
        // Retrieve all documents
        List<Document_Data> document = dataService.getAllDocs();

        // Prepare model object
        List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();

        for (Document_Data doc : document) {
            // Create new data transfer object
            DocumentDTO dto = new DocumentDTO();

            dto.setDocument_id(doc.getDocument_id());
            dto.setDocument_name(doc.getDocument_name());
            dto.setDocument_description(doc.getDocument_description());
            dto.setFile_path(doc.getFile_path());
            dto.setFile_size(doc.getFile_size());
            dto.setDocument_status(doc.getDocument_status());
            dto.setDocument_tag(doc.getDocument_tag());
            dto.setDocument_type(doc.getDocument_type());
            dto.setCreation_date(doc.getCreation_date());
            dto.setValid_from(doc.getValid_from());
            dto.setValid_to(doc.getValid_to());
            dto.setVertical_data(doc.getVertical_data());
            dto.setDocument_link(linkService.getAllDocumentsLink(doc.getDocument_id()));
            // Add to model list
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
	/* 							   Add Method 							      	*/

    @RequestMapping(value = "/addDocument", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Document_Data> addDocument(@RequestBody Document_Data document,
                                                     @ModelAttribute Document_Link document_link1,
                                                     HttpSession session){
        // public String postAdd(@ModelAttribute("documentAttribute") Document_Data document,HttpSession session) {
        logger.debug("Received request to add new record");
        session.removeAttribute("document_id");

        Document_Data document1 = (Document_Data) session.getAttribute("document1");
        if (document1 != null){
            document.setFile_size(document1.getFile_size());
            document.setFile_path(document1.getFile_path());
            document.setDocument_description(document1.getDocument_description());
            document.setDocument_name(document1.getDocument_name());
        }

        // Delegate to service
        dataService.RegisterOrUpdate(document);

        Document_Data doc = document;

        document_link1.setDocument_data(doc);

        Document_Link document_link2 = document_link1;
        session.setAttribute("document_id", document_link2);

        session.removeAttribute("document1");

        logger.debug("Added:: " + document);
        return new ResponseEntity<Document_Data>(document, HttpStatus.CREATED);
    }

	/* 							Add File Method 						 	*/

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @Produces("application/json")
    @Consumes("application/pdf")
    public ResponseEntity<Document_Data> uploadDocument(@RequestParam("file")
                                                                MultipartFile multipartFile,HttpSession session){
	/* @RequestBody Document_Data document */
        Document_Data document1 = new Document_Data();


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
                document1.setDocument_name(multipartFile.getOriginalFilename());
                document1.setDocument_description(multipartFile.getContentType());
                document1.setFile_size(multipartFile.getSize());
                document1.setFile_path(multipartFile.getBytes());

                session.setAttribute("document1", document1);

                logger.debug("Added:: " + document1);

                return new ResponseEntity<Document_Data>(document1,HttpStatus.CREATED);
            }
            catch (Exception e){
                return new ResponseEntity<Document_Data>(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<Document_Data>(HttpStatus.BAD_REQUEST);
        }

    }

}
