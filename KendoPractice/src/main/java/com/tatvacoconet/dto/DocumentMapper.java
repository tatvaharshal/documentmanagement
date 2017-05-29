package com.tatvacoconet.dto;

import org.springframework.stereotype.Component;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;

@Component
public class DocumentMapper {
    /**
     *
     * @return conversion from DocumentEntity to DocumentDTO
     */
    public DocumentDTO convertToDocumentDto(DocumentData document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setAddressScope(document.getAddressScope());
        documentDTO.setDocumentType(document.getDocumentType());
        documentDTO.setDocumentStatus(document.getDocumentStatus());
        documentDTO.setCreationDate(document.getCreationDate());
        documentDTO.setDocumentId(document.getDocumentId());
        documentDTO.setValidFrom(document.getValidFrom());
        documentDTO.setValidTo(document.getValidTo());
        documentDTO.setImportDate(document.getCreationDate());
        documentDTO.setDocumentTag(document.getDocumentTag());
        documentDTO.setDocumentName(document.getDocumentName());
        documentDTO.setDocumentDescription(document.getDocumentDescription());
        documentDTO.setFilePath(document.getFilePath());
        documentDTO.setFileSize(document.getFileSize());
        documentDTO.setVerticalData(document.getVerticalData());
        return documentDTO;
    }
    /**
     *
     * @return conversion from DocumentDTO to DocumentEntity
     */
    public DocumentData convertToDocumentEntity(DocumentDTO documentDTO) {
        DocumentData document = new DocumentData();
        document.setDocumentName(documentDTO.getDocumentName());
        document.setDocumentDescription(documentDTO.getDocumentDescription());
        document.setAddressScope(documentDTO.getAddressScope());
        document.setDocumentType(documentDTO.getDocumentType());
        document.setDocumentStatus(documentDTO.getDocumentStatus());
        document.setCreationDate(documentDTO.getCreationDate());
        document.setValidFrom(documentDTO.getValidFrom());
        document.setValidTo(documentDTO.getValidTo());
        document.setDocumentTag(documentDTO.getDocumentTag());
        document.setFilePath(documentDTO.getFilePath());
        document.setFileSize(documentDTO.getFileSize());
        document.setImportDate(document.getCreationDate());
        document.setDocumentId(documentDTO.getDocumentId());
        document.setVerticalData(documentDTO.getVerticalData());
        return document;
    }
    /**
     *
     * @return conversion from DocumentLinkEntity to DocumentLinkDTO
     */
    public DocumentLinkDTO convertToDocumentLinkDto(DocumentLink documentLink) {
        DocumentLinkDTO documentLinkDTO = new DocumentLinkDTO();
        documentLinkDTO.setDocumentLinkId(documentLink.getDocumentLinkId());
        documentLinkDTO.setGroupDetails(documentLink.getGroupDetails());
        documentLinkDTO.setRoleDetails(documentLink.getRoleDetails());
        documentLinkDTO.setUserId(documentLink.getUserId());
        documentLinkDTO.setDocumentData(documentLink.getDocumentData());
        return documentLinkDTO;
    }
    /**
     *
     * @return conversion from DocumentLinkDTO to DocumentLinkEntity
     */
    public DocumentLink convertToDocumentLinkEntity(DocumentLinkDTO documentLinkDTO) {
        DocumentLink documentLink = new DocumentLink();
        documentLink.setDocumentLinkId(documentLinkDTO.getDocumentLinkId());
        documentLink.setGroupDetails(documentLinkDTO.getGroupDetails());
        documentLink.setRoleDetails(documentLinkDTO.getRoleDetails());
        documentLink.setUserId(documentLinkDTO.getUserId());
        documentLink.setDocumentData(documentLinkDTO.getDocumentData());
        return documentLink;
    }
}
