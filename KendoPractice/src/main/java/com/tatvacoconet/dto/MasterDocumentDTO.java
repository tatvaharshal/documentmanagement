package com.tatvacoconet.dto;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.GroupDetails;
import com.tatvacoconet.entity.RoleDetails;
import com.tatvacoconet.entity.VerticalData;
import com.tatvacoconet.entity.DocumentStatus;
import com.tatvacoconet.entity.DocumentType;
import com.tatvacoconet.entity.DocumentTag;


import java.util.Date;

/**
 * Created by pca48 on 5/3/2017.
 */
public class MasterDocumentDTO {
    private long masterDocumentId;
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
    private long documentLinkId;
    private long userId;
    private String firstName;
    private String lastName;
    private GroupDetails groupDetails;
    private RoleDetails roleDetails;

    public long getMasterDocumentId() {
        return masterDocumentId;
    }

    public void setMasterDocumentId(long masterDocumentId) {
        this.masterDocumentId = masterDocumentId;
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

    public long getDocumentLinkId() {
        return documentLinkId;
    }

    public void setDocumentLinkId(long documentLinkId) {
        this.documentLinkId = documentLinkId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GroupDetails getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(GroupDetails groupDetails) {
        this.groupDetails = groupDetails;
    }

    public RoleDetails getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(RoleDetails roleDetails) {
        this.roleDetails = roleDetails;
    }
}
