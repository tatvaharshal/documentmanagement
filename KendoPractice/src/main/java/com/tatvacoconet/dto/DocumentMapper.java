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
        documentDTO.setCreationDate(document.getCreationDate());
        documentDTO.setDocumentId(document.getDocumentId());
        documentDTO.setDocumentDescription(document.getDocumentDescription());
        documentDTO.setDocumentName(document.getDocumentName());
        documentDTO.setDocumentStatus(document.getDocumentStatus());
        documentDTO.setDocumentTag(document.getDocumentTag());
        documentDTO.setDocumentType(document.getDocumentType());
        documentDTO.setFilePath(document.getFilePath());
        documentDTO.setFileSize(document.getFileSize());
        documentDTO.setImportDate(document.getImportDate());
        documentDTO.setValidFrom(document.getValidFrom());
        documentDTO.setValidTo(document.getValidTo());
        documentDTO.setVerticalData(document.getVerticalData());
        documentDTO.setAddressScope(document.getAddressScope());

        return documentDTO;
    }

	/* conversion from DTO to an entity: */

    public DocumentData convertToDocumentEntity(DocumentDTO documentDTO) {
        // Document_Data document = documentMapper.map(documentDTO,Document_Data.class);
        DocumentData document = new DocumentData();
        document.setCreationDate(documentDTO.getCreationDate());
        document.setDocumentId(documentDTO.getDocumentId());
        document.setDocumentDescription(documentDTO.getDocumentDescription());
        document.setDocumentName(documentDTO.getDocumentName());
        document.setDocumentStatus(documentDTO.getDocumentStatus());
        document.setDocumentTag(documentDTO.getDocumentTag());
        document.setDocumentType(documentDTO.getDocumentType());
        document.setFilePath(documentDTO.getFilePath());
        document.setFileSize(documentDTO.getFileSize());
        document.setImportDate(documentDTO.getImportDate());
        document.setValidFrom(documentDTO.getValidFrom());
        document.setValidTo(documentDTO.getValidTo());
        document.setVerticalData(documentDTO.getVerticalData());
        document.setAddressScope(documentDTO.getAddressScope());

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
