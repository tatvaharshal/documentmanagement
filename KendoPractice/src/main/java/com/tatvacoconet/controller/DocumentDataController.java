package com.tatvacoconet.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.DocumentLinkDTO;
import com.tatvacoconet.dto.DocumentMapper;
import com.tatvacoconet.dto.FieldErrorDTO;
import com.tatvacoconet.entity.AddressScope;
import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;
import com.tatvacoconet.entity.FileUpload;
import com.tatvacoconet.entity.VerticalData;
import com.tatvacoconet.service.DocumentDataService;
import com.tatvacoconet.service.DocumentLinkService;

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

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    /**
     *  List Of all Document Method
     * @return DocumentDTO with List Of all Document
     */
    @RequestMapping(value = "/DocumentList", method = RequestMethod.GET)
    public ModelAndView displayDocumentList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("DocumentList");
        return mav;
    }
    @RequestMapping(value = "/documentList", method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        /*
         DocumentData List method first
         */
        List<DocumentData> document = dataService.getAllDocs();
        List<DocumentDTO> documentDTO = new ArrayList<>();
        for (DocumentData doc : document) {
            DocumentDTO dto = documentMapper.convertToDocumentDto(doc);
            /*
              DocumentLink Table Data from Perticular DocumentData Id
             */
            List<DocumentLink> documentlist = linkService.getAllDocumentsLink(doc.getDocumentId());
            for (DocumentLink doclist : documentlist) {
                DocumentLinkDTO documentlinkdto = documentMapper.convertToDocumentLinkDto(doclist);
                dto.setDocumentLinkDTO(documentlinkdto);
            }
            documentDTO.add(dto);
        }
        if (documentDTO.isEmpty()) {
            logger.debug("document does not exists");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            logger.debug("Found " + documentDTO.size() + " document");
            logger.debug(Arrays.toString(documentDTO.toArray()));
            return new ResponseEntity<>(documentDTO,HttpStatus.OK);
        }
    }
    /**
     *  Save Document Method
     * @return DocumentDTO saved DocumentData
     */
    @RequestMapping(value = "/DocumentAdd", method = RequestMethod.GET)
    public ModelAndView displayAddDocument() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("DocumentAdd");
        return mav;
    }
    @RequestMapping(value = "/DocumentAdd", method = RequestMethod.POST)
    public ResponseEntity<DocumentDTO> addDocument(@RequestBody DocumentDTO documentDTO,
                                                   @ModelAttribute DocumentData document,
                                                   @ModelAttribute DocumentLink documentLink)
                                                   throws ParseException {
        logger.debug("Received request to add new record");
        DocumentLinkDTO docLinkDTO =new DocumentLinkDTO();
        DocumentDTO documentErrorDTO= new DocumentDTO();
        DocumentDTO documentIdDTO= new DocumentDTO();
        DocumentDTO documentLinkErrorDTO= new DocumentDTO();
        String currentDate=dateFormat.format(date);

        if(documentDTO.getValidFrom()!=null){
            String validFrom=dateFormat.format(documentDTO.getValidFrom());
            Date validFromDate=new SimpleDateFormat("yyyy/MM/dd").parse(validFrom);
            documentDTO.setValidFrom(validFromDate);
        }
        if(documentDTO.getValidTo()!=null){
            String vaidTo=dateFormat.format(documentDTO.getValidTo());
            Date vaidToDate=new SimpleDateFormat("yyyy/MM/dd").parse(vaidTo);
            documentDTO.setValidTo(vaidToDate);
        }
        Date todayDate=new SimpleDateFormat("yyyy/MM/dd").parse(currentDate);
        /*
          Validation Check For DocumentData Table
         */
        List<FieldErrorDTO> fieldErrors=documentValidator.validateDocument(documentDTO);
        if (fieldErrors.size()>0) {
            dataService.delete(documentDTO.getDocumentId());
            documentErrorDTO.setFieldErrorDTO(fieldErrors);
            logger.info("Validation Error in DocumentData"+documentErrorDTO);
            return new ResponseEntity<>(documentErrorDTO,HttpStatus.BAD_REQUEST);
        }
        else {
            document = dataService.getDocumentByID(documentDTO.getDocumentId());
            if (document == null) {
                FieldErrorDTO error = new FieldErrorDTO("documentId","Unable to Save. "
                        + "DocumentData with id " + documentDTO.getDocumentId() + " not found");
                fieldErrors.add(error);
                documentIdDTO.setFieldErrorDTO(fieldErrors);
                logger.info("Unable to Save.DocumentData with id " + documentDTO.getDocumentId() + " not found");
                return new ResponseEntity<>(documentIdDTO,HttpStatus.NOT_FOUND);
            }
            if(documentDTO.getCreationDate()!=null){
                document.setCreationDate(documentDTO.getCreationDate());
            }
            else{
                document.setCreationDate(todayDate);
            }
            document.setImportDate(todayDate);
            document.setDocumentDescription(documentDTO.getDocumentDescription());
            if(documentDTO.getDocumentName()!=null) {
                document.setDocumentName(documentDTO.getDocumentName());
            }
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setDocumentTag(documentDTO.getDocumentTag());
            document.setDocumentType(documentDTO.getDocumentType());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            if(documentDTO.getAddressScope()==null) {
                document.setAddressScope(AddressScope.None);
            }
            else{
                document.setAddressScope(documentDTO.getAddressScope());
            }
            document.setVerticalData(VerticalData.BOI);
            dataService.updateDocument(document);
            logger.info("DocumentData created successfully"+document);
        }
        DocumentLinkDTO documentlinkdto = documentDTO.getDocumentLinkDTO();
        /*
          Validation Check For DocumentLink Table
         */
        if(documentlinkdto!=null){
            List<FieldErrorDTO> fieldError=documentLinkValidator.validateDocumentLink(documentlinkdto);
            if (fieldError.size()>0) {
                dataService.delete(document.getDocumentId());
                documentLinkErrorDTO.setFieldErrorDTO(fieldError);
                logger.info("Validation Error in DocumentLink"+documentLinkErrorDTO);
                return new ResponseEntity<>(documentLinkErrorDTO,HttpStatus.BAD_REQUEST);
            }
            else {
                documentlinkdto.setDocumentData(document);
                documentLink = documentMapper.convertToDocumentLinkEntity(documentlinkdto);
                linkService.addDocument(documentLink);
                logger.info("DocumentLink Data created successfully"+documentLink);
            }
        }
        else{
            docLinkDTO.setDocumentData(document);
            documentLink = documentMapper.convertToDocumentLinkEntity(docLinkDTO);
            linkService.addDocument(documentLink);
            logger.info("DocumentLink Data created successfully"+documentLink);
        }
        logger.info("Data created successfully");
        return new ResponseEntity<>(documentDTO, HttpStatus.CREATED);
    }
    /*File Upload Method
      @return DocumentDTO saved FileData
     */
    @RequestMapping(value = "/fileUpload", produces = "application/json",consumes = "multipart/*",
                                                                      method = RequestMethod.POST)
    public ResponseEntity<DocumentDTO> uploadDocument(FileUpload file) throws IOException {
        DocumentDTO documentDTO = new DocumentDTO();
        DocumentDTO fileErrorDTO = new DocumentDTO();
        DocumentDTO documentFileDTO;new DocumentDTO();
        DocumentData document ;new DocumentData();
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();

        MultipartFile multipartFile = file.getFile();
        if (!multipartFile.isEmpty()) {
            Random r = new Random();
            int random = r.nextInt(50);
            documentDTO.setDocumentName(random+"-"+multipartFile.getOriginalFilename());
            documentDTO.setFileSize(multipartFile.getSize() / 1024);
            documentDTO.setFilePath(multipartFile.getBytes());
            /*
              Validation Check For Uploaded File
             */
            List<FieldErrorDTO> FilefieldErrors=documentValidator.validateFileDetails(documentDTO);
            if (FilefieldErrors.size()>0) {
                fileErrorDTO.setFieldErrorDTO(FilefieldErrors);
                logger.info("Validation Error in FileUpload"+fileErrorDTO);
                return new ResponseEntity<>(fileErrorDTO,HttpStatus.BAD_REQUEST);
            }
            document = documentMapper.convertToDocumentEntity(documentDTO);
            DocumentData documentUpload = dataService.RegisterOrUpdate(document);
            documentFileDTO = documentMapper.convertToDocumentDto(documentUpload);
        }
        else
        {
            FieldErrorDTO error = new FieldErrorDTO("filePath","Proper Pdf File Not Found");
            fieldErrors.add(error);
            documentDTO.setFieldErrorDTO(fieldErrors);
            logger.info("Proper Pdf File Not Found");
            return new ResponseEntity<>(documentDTO,HttpStatus.NOT_FOUND);
        }
        logger.info("File Uploaded successfully");
        return new ResponseEntity<>(documentFileDTO, HttpStatus.CREATED);
    }
    /**
     *  List Of Document By Id Method
     * @return DocumentDTO List of Data of Perticular Id
     */
    @RequestMapping(value = "/DocumentDetail", method = RequestMethod.GET)
    public ModelAndView DocumentDetail() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("DocumentDetail");
        return mav;
    }
    @RequestMapping(value = "/documentListByID/{documentId}", method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> getDocumentsByID(@PathVariable("documentId") long documentId) {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        if (documentId=='\0') {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DocumentDTO documentDTO = new DocumentDTO();
        DocumentData document = dataService.getDocumentByID(documentId);
        if(document==null){
            FieldErrorDTO error = new FieldErrorDTO("documentId","Unable to FindDetails."
                    + "DocumentData with id " + documentId + " not found");
            fieldErrors.add(error);
            documentDTO.setFieldErrorDTO(fieldErrors);
            logger.info("Unable to FindDetails. DocumentData with id " + documentId + " not found");
            return new ResponseEntity<>(documentDTO,HttpStatus.NOT_FOUND);
        }
        else{
            documentDTO = documentMapper.convertToDocumentDto(document);
            List<DocumentLink> documentLink = linkService.getAllDocumentsLink(documentId);
            for (DocumentLink doclist : documentLink) {
                DocumentLinkDTO documentlinkdto = documentMapper.convertToDocumentLinkDto(doclist);
                documentDTO.setDocumentLinkDTO(documentlinkdto);
            }
        }
        return new ResponseEntity<>(documentDTO, HttpStatus.OK);
    }
    /**
     * FileDownload method
     * @return File Download from browser.
     */
    @RequestMapping(value = "/download/{documentId}", method = RequestMethod.GET)
    public ResponseEntity <DocumentDTO> download(@PathVariable("documentId") int documentId,
                                                 HttpServletResponse response)throws IOException {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentDTO = new DocumentDTO();
        if (documentId=='\0') {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DocumentData document = dataService.getDocumentByID(documentId);
        if(document==null){
            FieldErrorDTO error = new FieldErrorDTO("documentId","Unable to FindDetails."
                    + "DocumentData with id " + documentId + " not found");
            fieldErrors.add(error);
            documentDTO.setFieldErrorDTO(fieldErrors);
            logger.info("Unable to FindDetails. DocumentData with id " + documentId + " not found");
            return new ResponseEntity<>(documentDTO,HttpStatus.NOT_FOUND);
        }
        else {
            String filename=document.getDocumentName();
            String fileName=filename.substring(filename.lastIndexOf("-") + 1);
            if(!fileName.endsWith(".pdf"))
            {
                fileName = fileName.concat(".pdf");
            }
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
            outputStream.write(document.getFilePath());
            outputStream.flush();
            outputStream.close();
            logger.info("File Downloaded successfully");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /*
      Document Update method
      @return Updated DocumentDTO.
      @throws ParseException
     */
    @RequestMapping(value = "/updateDocument", method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> updateDocument(@RequestBody DocumentDTO documentDTO,
                                                      @ModelAttribute DocumentData document,
                                                      @ModelAttribute DocumentLink documentLink) throws ParseException {
        DocumentDTO documentUpdateErrorDTO=new DocumentDTO();
        DocumentDTO documentLinkUpdateErrorDTO=new DocumentDTO();
        String importDate=dateFormat.format(documentDTO.getImportDate());
        if(documentDTO.getValidFrom()!=null){
            String validFrom=dateFormat.format(documentDTO.getValidFrom());
            Date validFromDate=new SimpleDateFormat("yyyy/MM/dd").parse(validFrom);
            documentDTO.setValidFrom(validFromDate);
        }
        if(documentDTO.getValidTo()!=null){
            String vaidTo=dateFormat.format(documentDTO.getValidTo());
            Date vaidToDate=new SimpleDateFormat("yyyy/MM/dd").parse(vaidTo);
            documentDTO.setValidTo(vaidToDate);
        }
        Date importDateData=new SimpleDateFormat("yyyy/MM/dd").parse(importDate);

        List<FieldErrorDTO> fieldErrors=documentValidator.validateUpdateDocument(documentDTO);
        if (fieldErrors.size()>0) {
            documentUpdateErrorDTO.setFieldErrorDTO(fieldErrors);
            logger.info("Validation Error in UpdateDocument"+documentUpdateErrorDTO);
            return new ResponseEntity<>(documentUpdateErrorDTO,HttpStatus.BAD_REQUEST);
        }
        document = dataService.getDocumentByID(documentDTO.getDocumentId());
        if (document.getAddressScope() == AddressScope.None) {
            document.setImportDate(importDateData);
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
            logger.info("DocumentData Updated successfully");
            DocumentLink documentLinkById = linkService.getAllDocumentsById(document.getDocumentId());
            DocumentLinkDTO documentlinkdto = documentDTO.getDocumentLinkDTO();
            List<FieldErrorDTO> fieldErrorsDTO=documentLinkValidator.validateDocumentLink(documentlinkdto);

            if (fieldErrors.size()>0) {
                documentLinkUpdateErrorDTO.setFieldErrorDTO(fieldErrorsDTO);
                logger.info("Validation Error in UpdateDocumentLink "+documentLinkUpdateErrorDTO);
                return new ResponseEntity<>(documentLinkUpdateErrorDTO,HttpStatus.BAD_REQUEST);
            }
            documentLink = documentMapper.convertToDocumentLinkEntity(documentlinkdto);
            documentLinkById.setGroupDetails(documentLink.getGroupDetails());
            documentLinkById.setRoleDetails(documentLink.getRoleDetails());
            documentLinkById.setUserId(documentLink.getUserId());
            linkService.addDocument(documentLinkById);
            logger.info("DocumentLink Data Updated successfully");
            documentlinkdto = documentMapper.convertToDocumentLinkDto(documentLinkById);
            documentDTO.setDocumentLinkDTO(documentlinkdto);
        }
        else {
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            document.setDocumentDescription(documentDTO.getDocumentDescription());
            document.setDocumentTag(documentDTO.getDocumentTag());
            dataService.updateDocument(document);
            logger.info("DocumentData Updated successfully");
        }
        logger.info("DocumentUpdated successfully");
        return new ResponseEntity<>(documentDTO, HttpStatus.CREATED);
    }

    /**
     * Document Delete method
     *
     */
    @RequestMapping(value = "/deleteDocument/{documentId}", method = RequestMethod.GET)
    public ResponseEntity<DocumentDTO> getDelete(@PathVariable("documentId") long documentId) {
        logger.debug("Received request to delete record");
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentDTO = new DocumentDTO();
       if (documentId == '\0') {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DocumentData document = dataService.getDocumentByID(documentId);
        if (document != null) {
            linkService.deleteAll(documentId);
            dataService.delete(documentId);
        }
        else {
            FieldErrorDTO error = new FieldErrorDTO("documentId",
                    "Unable to delete. DocumentData with id " + documentId + " not found");
            fieldErrors.add(error);
            documentDTO.setFieldErrorDTO(fieldErrors);
            logger.info("Unable to delete. DocumentData with id " + documentId + " not found");
            return new ResponseEntity<>(documentDTO,HttpStatus.NOT_FOUND);
        }
        logger.info("Document Deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

