package com.tatvacoconet.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tatvacoconet.entity.DocumentLink;
import com.tatvacoconet.service.DocumentDataService;
import com.tatvacoconet.service.DocumentLinkService;

@RestController
public class DocumentLinkController {
    final static Logger logger = Logger.getLogger(DocumentLinkController.class);

    @Autowired
    DocumentDataService dataService;

    @Autowired
    DocumentLinkService linkService;

    @RequestMapping(value = "/addDocumentLink", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<DocumentLink> addDocument(@RequestBody DocumentLink documentlink, HttpSession session){

        logger.debug("Received request to add new credit card");

        DocumentLink documentlink1 = (DocumentLink) session.getAttribute("documentid");
        documentlink.setDocumentData(documentlink1.getDocumentData());

        // document_link.setDocumentdata(dataService.getDocumentByID(documentid));
        // Delegate to service
        linkService.addDocument(documentlink);

        // session.removeAttribute("documentid");
        logger.debug("Added:: " + documentlink);
        return new ResponseEntity<DocumentLink>(documentlink,HttpStatus.CREATED);

    }

}
