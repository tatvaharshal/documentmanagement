package com.tatvacoconet.dto;

import com.tatvacoconet.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by pca48 on 5/3/2017.
 */
public class DocumentDTO {
    private long documentId;
    private String documentName;
    private String documentDescription;
    private long fileSize;
    private byte[] filePath;
    private Date importDate;
    private Date creationDate;
    private Date validFrom;
    private Date validTo;
    private VerticalData verticalData;
    private String documentStatus;
    private String documentType;
    private String documentTag;
    private AddressScope addressScope;
    private List<FieldErrorDTO> fieldErrorDTO;
    private DocumentLinkDTO documentLinkDTO;
    public DocumentDTO(){}
    public DocumentDTO(String documentDescription,String documentTag,Date creationDate,
                       Date importDate,Date validFrom,Date validTo,
                       VerticalData verticalData,String documentStatus,
                       String documentType,AddressScope addressScope,
                       DocumentLinkDTO documentLinkDTO) {
        this.documentDescription=documentDescription;
        this.documentTag=documentTag;
        this.creationDate=creationDate;
        this.importDate=importDate;
        this.validFrom=validFrom;
        this.validTo=validTo;
        this.verticalData=verticalData;
        this.documentStatus=documentStatus;
        this.documentType=documentType;
        this.addressScope=addressScope;
        this.documentLinkDTO=documentLinkDTO;
    }

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

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
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

    public String getDocumentStatus() { return documentStatus; }

    public void setDocumentStatus(String documentStatus) { this.documentStatus = documentStatus; }

    public String getDocumentType() { return documentType; }

    public void setDocumentType(String documentType) { this.documentType = documentType;}

    public String getDocumentTag() {
        return documentTag;
    }

    public void setDocumentTag(String documentTag) {
        this.documentTag = documentTag;
    }

    public List<FieldErrorDTO> getFieldErrorDTO() { return fieldErrorDTO; }

    public void setFieldErrorDTO(List<FieldErrorDTO> fieldErrorDTO) { this.fieldErrorDTO = fieldErrorDTO;}

    public DocumentLinkDTO  getDocumentLinkDTO() { return documentLinkDTO; }

    public void setDocumentLinkDTO(DocumentLinkDTO documentLinkDTO) { this.documentLinkDTO = documentLinkDTO; }

    public AddressScope getAddressScope() {
        return addressScope;
    }

    public void setAddressScope(AddressScope addressScope) {
        this.addressScope = addressScope;
    }
}
