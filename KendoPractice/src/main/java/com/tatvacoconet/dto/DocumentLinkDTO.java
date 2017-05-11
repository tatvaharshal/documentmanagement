package com.tatvacoconet.dto;

import com.tatvacoconet.entity.AddressScope;
import com.tatvacoconet.entity.DocumentData;

/**
 * Created by pca48 on 5/10/2017.
 */
public class DocumentLinkDTO {

    private long documentLinkId;
    private long userId;
    private String groupDetails;
    private String roleDetails;

    private DocumentData documentData;

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

    public String getGroupDetails() {
        return groupDetails;
    }

    public void setGroupDetails(String groupDetails) {
        this.groupDetails = groupDetails;
    }

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
