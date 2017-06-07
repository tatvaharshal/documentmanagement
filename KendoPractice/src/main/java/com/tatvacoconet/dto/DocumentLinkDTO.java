package com.tatvacoconet.dto;

import com.tatvacoconet.entity.DocumentData;

public class DocumentLinkDTO {
    private long documentLinkId;
    private String userId;
    private String groupDetails;
    private String roleDetails;
    private DocumentData documentData;

    public DocumentLinkDTO(){}
    public DocumentLinkDTO(String userId,String groupDetails,String roleDetails) {
        this.userId=userId;
        this.groupDetails=groupDetails;
        this.roleDetails=roleDetails;
    }
    public long getDocumentLinkId() {
        return documentLinkId;
    }

    public void setDocumentLinkId(long documentLinkId) {
        this.documentLinkId = documentLinkId;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(String groupDetails) { this.groupDetails = groupDetails; }

    public String getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(String roleDetails) {
        this.roleDetails = roleDetails;
    }

    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }
}
