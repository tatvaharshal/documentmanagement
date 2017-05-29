package com.tatvacoconet.controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
/* @RequestMapping("/document") */
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



    @RequestMapping(value = "/documentList", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        /** *   DocumentData List method first   */
        List<DocumentData> document = dataService.getAllDocs();
        List<DocumentDTO> documentDTO = new ArrayList<DocumentDTO>();
        for (DocumentData doc : document) {
            DocumentDTO dto = documentMapper.convertToDocumentDto(doc);
            /**  * DocumentLink Table Data from Perticular DocumentData Id  */
            List<DocumentLink> documentlist = linkService.getAllDocumentsLink(doc.getDocumentId());
            for (DocumentLink doclist : documentlist) {
                DocumentLinkDTO documentlinkdto = documentMapper.convertToDocumentLinkDto(doclist);
                dto.setDocumentLinkDTO(documentlinkdto);
            }
            documentDTO.add(dto);
        }
        if (documentDTO.isEmpty()) {
            return new ResponseEntity<List<DocumentDTO>>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<List<DocumentDTO>>(documentDTO,HttpStatus.OK);
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
                                                   @ModelAttribute DocumentLink documentLink,
                                                   Model model, HttpServletRequest request)
            throws ParseException {
        DocumentLinkDTO docLinkDTO =new DocumentLinkDTO();
        DocumentDTO documentErrorDTO= new DocumentDTO();
        DocumentDTO documentLinkErrorDTO= new DocumentDTO();

        /**  *      Validation Check For DocumentData Table      * */
        List<FieldErrorDTO> fieldErrors=documentValidator.validateDocument(documentDTO);
        if (fieldErrors.size()>0) {
            dataService.delete(documentDTO.getDocumentId());
            documentErrorDTO.setFieldErrorDTO(fieldErrors);
            return new ResponseEntity<DocumentDTO>(documentErrorDTO,HttpStatus.BAD_REQUEST);
        }
        else {
            document = dataService.getDocumentByID(documentDTO.getDocumentId());
            document.setCreationDate(documentDTO.getCreationDate());
            document.setImportDate(documentDTO.getCreationDate());
            if(documentDTO.getDocumentDescription()!=null){
                document.setDocumentDescription(documentDTO.getDocumentDescription());
            }
            if(documentDTO.getDocumentName()!=null) {
                document.setDocumentName(documentDTO.getDocumentName());
            }
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setDocumentTag(documentDTO.getDocumentTag());
            document.setDocumentType(documentDTO.getDocumentType());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            document.setAddressScope(documentDTO.getAddressScope());
            document.setVerticalData(VerticalData.BOI);
            dataService.updateDocument(document);
        }
        DocumentLinkDTO documentlinkdto = documentDTO.getDocumentLinkDTO();
        /**  *      Validation Check For DocumentLink Table      * */
        if(documentlinkdto!=null){
            List<FieldErrorDTO> fieldErrors1=documentLinkValidator.validateDocumentLink(documentlinkdto);
            if (fieldErrors1.size()>0) {
                dataService.delete(document.getDocumentId());
                documentLinkErrorDTO.setFieldErrorDTO(fieldErrors1);
                return new ResponseEntity<DocumentDTO>(documentLinkErrorDTO,HttpStatus.BAD_REQUEST);
            }
            else {
                documentlinkdto.setDocumentData(document);
                documentLink = documentMapper.convertToDocumentLinkEntity(documentlinkdto);
                linkService.addDocument(documentLink);
            }
        }
        else{
            docLinkDTO.setDocumentData(document);
            documentLink = documentMapper.convertToDocumentLinkEntity(docLinkDTO);
            linkService.addDocument(documentLink);
        }
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }
    /**
     *  File Upload Method
     * @return DocumentDTO saved FileData
     */
    @RequestMapping(value = "/fileUpload", produces = "application/json",consumes = "multipart/*",
            method = RequestMethod.POST)
    public ResponseEntity<DocumentDTO> uploadDocument(FileUpload file) throws IOException {
        DocumentDTO documentDTO = new DocumentDTO();
        DocumentDTO fileErrorDTO = new DocumentDTO();
        DocumentDTO documentFileDTO = new DocumentDTO();
        DocumentData document = new DocumentData();

        MultipartFile multipartFile = file.getFile();
        if (!multipartFile.isEmpty()) {
            Random r = new Random();
            int random = r.nextInt(50);
            documentDTO.setDocumentName(random+"-"+multipartFile.getOriginalFilename());
            documentDTO.setDocumentDescription(multipartFile.getContentType());
            documentDTO.setFileSize(multipartFile.getSize() / 1024);
            documentDTO.setFilePath(multipartFile.getBytes());
            /**  *     Validation Check For Uploaded File      * */
            List<FieldErrorDTO> fieldErrors=documentValidator.validateFileDetails(documentDTO);
            if (fieldErrors.size()>0) {
                fileErrorDTO.setFieldErrorDTO(fieldErrors);
                return new ResponseEntity<DocumentDTO>(fileErrorDTO,HttpStatus.BAD_REQUEST);
            }
            document = documentMapper.convertToDocumentEntity(documentDTO);
            DocumentData documentUpload = dataService.RegisterOrUpdate(document);
            documentFileDTO = documentMapper.convertToDocumentDto(documentUpload);
        }
        return new ResponseEntity<DocumentDTO>(documentFileDTO, HttpStatus.CREATED);
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

        DocumentDTO documentDTO = new DocumentDTO();
        DocumentData document = dataService.getDocumentByID(documentId);
        documentDTO = documentMapper.convertToDocumentDto(document);

        List<DocumentLink> documentLink = linkService.getAllDocumentsLink(documentId);
        for (DocumentLink doclist : documentLink) {
            DocumentLinkDTO documentlinkdto = documentMapper.convertToDocumentLinkDto(doclist);
            documentDTO.setDocumentLinkDTO(documentlinkdto);
        }
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.OK);
    }
    /**
     * FileDownload method
     * @return File Download from browser.
     */
    @RequestMapping(value = "/download/{documentId}", method = RequestMethod.GET)
    public void download(@PathVariable("documentId") int documentId,HttpServletRequest request,
                         HttpServletResponse response)throws IOException {
        DocumentData document = dataService.getDocumentByID(documentId);
        String filename=document.getDocumentName();
        String fileName=filename.substring(filename.lastIndexOf("-") + 1);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
        outputStream.write(document.getFilePath());
        outputStream.flush();
        outputStream.close();
    }
    /**
     * Document Update method
     * @return Updated DocumentDTO.
     */
    @RequestMapping(value = "/updateDocument", method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<DocumentDTO> updateDocument(@RequestBody DocumentDTO documentDTO,
                                                      @ModelAttribute DocumentData document,
                                                      @ModelAttribute DocumentLink documentLink) {
        DocumentDTO documentdto1=new DocumentDTO();
        DocumentDTO documentdto2=new DocumentDTO();
        List<FieldErrorDTO> fieldErrors=documentValidator.validateDocument(documentDTO);
        if (fieldErrors.size()>0) {
            documentdto1.setFieldErrorDTO(fieldErrors);
            return new ResponseEntity<DocumentDTO>(documentdto1,HttpStatus.BAD_REQUEST);
        }
        document = dataService.getDocumentByID(documentDTO.getDocumentId());
        if (document.getAddressScope() == AddressScope.None) {
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
            DocumentLink documentLinkById = linkService.getAllDocumentsById(document.getDocumentId());
            DocumentLinkDTO documentlinkdto = documentDTO.getDocumentLinkDTO();
            List<FieldErrorDTO> fieldErrors1=documentLinkValidator.validateDocumentLink(documentlinkdto);

            if (fieldErrors.size()>0) {
                documentdto2.setFieldErrorDTO(fieldErrors1);
                return new ResponseEntity<DocumentDTO>(documentdto2,HttpStatus.BAD_REQUEST);
            }
            documentLink = documentMapper.convertToDocumentLinkEntity(documentlinkdto);
            documentLinkById.setGroupDetails(documentLink.getGroupDetails());
            documentLinkById.setRoleDetails(documentLink.getRoleDetails());
            documentLinkById.setUserId(documentLink.getUserId());
            linkService.addDocument(documentLinkById);
            documentlinkdto = documentMapper.convertToDocumentLinkDto(documentLinkById);
            documentDTO.setDocumentLinkDTO(documentlinkdto);
        }
        else {
            document.setDocumentStatus(documentDTO.getDocumentStatus());
            document.setValidFrom(documentDTO.getValidFrom());
            document.setValidTo(documentDTO.getValidTo());
            document.setDocumentTag(documentDTO.getDocumentTag());
            dataService.updateDocument(document);
        }
        return new ResponseEntity<DocumentDTO>(documentDTO, HttpStatus.CREATED);
    }
    /**
     * Document Delete method
     *
     */
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
