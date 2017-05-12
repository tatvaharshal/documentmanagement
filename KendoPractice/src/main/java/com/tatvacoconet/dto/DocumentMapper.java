package com.tatvacoconet.dto;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;
import org.springframework.stereotype.Component;

/**
 * Created by pca48 on 5/10/2017.
 */
@Component
public class DocumentMapper {

    /* conversion from entity to PostDto: */
    public DocumentDTO convertToDocumentDto(DocumentData document) {
        //DocumentDTO documentDTO = documentMapper.map(document, DocumentDTO.class);
        DocumentDTO documentDTO = new DocumentDTO();
          documentDTO.setAddressScope(document.getAddressScope());
          documentDTO.setDocumentType(document.getDocumentType());
          documentDTO.setDocumentStatus(document.getDocumentStatus());
          documentDTO.setCreationDate(document.getCreationDate());
          documentDTO.setDocumentId(document.getDocumentId());
          documentDTO.setValidFrom(document.getValidFrom());
          documentDTO.setValidTo(document.getValidTo());
          documentDTO.setImportDate(document.getImportDate());
          documentDTO.setDocumentTag(document.getDocumentTag());
          documentDTO.setDocumentName(document.getDocumentName());
          documentDTO.setDocumentDescription(document.getDocumentDescription());
          documentDTO.setFilePath(document.getFilePath());
          documentDTO.setFileSize(document.getFileSize());
          documentDTO.setVerticalData(document.getVerticalData());

        return documentDTO;
    }

	/* conversion from DTO to an entity: */

    public DocumentData convertToDocumentEntity(DocumentDTO documentDTO) {
        // Document_Data document = documentMapper.map(documentDTO,Document_Data.class);
        DocumentData document = new DocumentData();

        if(documentDTO.getDocumentName()!=null)
        {   document.setDocumentName(documentDTO.getDocumentName());}

        if(documentDTO.getDocumentDescription()!=null)
        {   document.setDocumentDescription(documentDTO.getDocumentDescription());}

        if(documentDTO.getAddressScope()!=null)
        {   document.setAddressScope(documentDTO.getAddressScope());}

        if(documentDTO.getDocumentType()!=null)
        {   document.setDocumentType(documentDTO.getDocumentType());}

        if(documentDTO.getDocumentStatus()!=null)
        {   document.setDocumentStatus(documentDTO.getDocumentStatus());}

        if(documentDTO.getCreationDate()!=null)
        {   document.setCreationDate(documentDTO.getCreationDate());}

        if(documentDTO.getValidFrom()!=null)
        {   document.setValidFrom(documentDTO.getValidFrom());}

        if(documentDTO.getValidTo()!=null)
        {   document.setValidTo(documentDTO.getValidTo());}

        if(documentDTO.getDocumentTag()!=null)
        {  document.setDocumentTag(documentDTO.getDocumentTag());}

        if(documentDTO.getFilePath()!=null)
        {  document.setFilePath(documentDTO.getFilePath());}

        if(documentDTO.getFileSize()!='\0')
        {  document.setFileSize(documentDTO.getFileSize());}

        document.setImportDate(documentDTO.getImportDate());
        document.setDocumentId(documentDTO.getDocumentId());
        document.setVerticalData(documentDTO.getVerticalData());
        return document;
    }

    /* conversion from entity to PostDto: */
    public DocumentLinkDTO convertToDocumentLinkDto(DocumentLink documentLink) {
        // DocumentLinkDTO documentLinkDTO = documentMapper.map(document_Link,DocumentLinkDTO.class);
        DocumentLinkDTO documentLinkDTO = new DocumentLinkDTO();
        documentLinkDTO.setDocumentLinkId(documentLink.getDocumentLinkId());
        documentLinkDTO.setGroupDetails(documentLink.getGroupDetails());
        documentLinkDTO.setRoleDetails(documentLink.getRoleDetails());
        documentLinkDTO.setUserId(documentLink.getUserId());
       // documentLinkDTO.setDocumentData(documentLink.getDocumentData());
        return documentLinkDTO;
    }

	/* conversion from DTO to an entity: */

    public DocumentLink convertToDocumentLinkEntity(DocumentLinkDTO documentLinkDTO) {
        // Document_Link document_Link = documentMapper.map(documentLinkDTO, Document_Link.class);
        DocumentLink documentLink = new DocumentLink();
        documentLink.setDocumentLinkId(documentLinkDTO.getDocumentLinkId());
        documentLink.setGroupDetails(documentLinkDTO.getGroupDetails());
        documentLink.setRoleDetails(documentLinkDTO.getRoleDetails());
        documentLink.setUserId(documentLinkDTO.getUserId());
        documentLink.setDocumentData(documentLinkDTO.getDocumentData());

        return documentLink;
    }

}
