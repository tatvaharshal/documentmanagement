package com.tatvacoconet.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "DocumentData")
public class DocumentData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "documentId", unique = true, nullable = false)
    private long documentId;

    @Column(name = "documentName", length = 255)
    private String documentName;
    @Column(name = "documentDescription",length = 1000)
    private String documentDescription;

    @NotNull
    @Column(name = "filePath")
    private byte[] filePath;

    @Column(name = "fileSize")
    private long fileSize;

    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "importDate")
    private Date importDate;

    @Column(name = "validFrom")
    private Date validFrom;

    @Column(name = "validTo")
    private Date validTo;

    @Enumerated(EnumType.STRING)
    private VerticalData verticalData;

    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    @Column(name = "documentType")
    private String documentType;

    @Column(name = "documentTag")
    private String documentTag;

    @Enumerated(EnumType.STRING)
    private AddressScope addressScope;

    public DocumentData() {
    }
    public DocumentData(String documentName,String documentDescription,long fileSize,Date creationDate,
                        Date importDate,Date validFrom,Date validTo,VerticalData verticalData,
                        DocumentStatus documentStatus,String documentType,AddressScope addressScope) {
        this.documentName=documentName;
        this.documentDescription=documentDescription;
        this.fileSize=fileSize;
        this.creationDate=creationDate;
        this.importDate=importDate;
        this.validFrom=validFrom;
        this.validTo=validTo;
        this.verticalData=verticalData;
        this.documentStatus=documentStatus;
        this.documentType=documentType;
        this.addressScope=addressScope;
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

    public String getDocumentType() { return documentType; }

    public void setDocumentType(String documentType) { this.documentType = documentType;}

    public String getDocumentTag() {
        return documentTag;
    }

    public void setDocumentTag(String documentTag) {
        this.documentTag = documentTag;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public AddressScope getAddressScope() {
        return addressScope;
    }

    public void setAddressScope(AddressScope addressScope) {
        this.addressScope = addressScope;
    }

}
