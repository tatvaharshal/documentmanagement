package com.tatvacoconet.dto;

import java.util.Date;
import java.util.List;

import com.tatvacoconet.entity.DocumentStatus;
import com.tatvacoconet.entity.DocumentTag;
import com.tatvacoconet.entity.DocumentType;
import com.tatvacoconet.entity.VerticalData;
import com.tatvacoconet.entity.DocumentLink;

public class DocumentDTO {
    private long documentId;
    private String documentName;
    private String documentDescription;
    private byte[] filePath;
    private long fileSize;
    private Date creationDate;
    private Date validFrom;
    private Date validTo;
    private VerticalData verticalData;
    private DocumentStatus documentStatus;
    private DocumentType documentType;
    private DocumentTag documentTag;
    private List<DocumentLink> documentLink;

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public byte[] getFilePath() {
        return filePath;
    }

    public void setFilePath(byte[] filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public VerticalData getVerticalData() {
        return verticalData;
    }

    public void setVerticalData(VerticalData verticalData) {
        this.verticalData = verticalData;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public DocumentTag getDocumentTag() {
        return documentTag;
    }

    public void setDocumentTag(DocumentTag documentTag) {
        this.documentTag = documentTag;
    }

    public List<DocumentLink> getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(List<DocumentLink> documentLink) {
        this.documentLink = documentLink;
    }
}
