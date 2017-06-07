package com.tatvacoconet.entity;

import javax.persistence.*;


@Entity
@Table(name = "DocumentLink")
public class DocumentLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "documentLinkId", unique = true, nullable = false)
    private long documentLinkId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "groupDetails")
    private String groupDetails;

    @Column(name = "roleDetails")
    private String roleDetails;

    @ManyToOne
    private DocumentData documentData;

    public long getDocumentLinkId() {
        return documentLinkId;
    }

    public void setDocumentLinkId(long documentLinkId) {
        this.documentLinkId = documentLinkId;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getGroupDetails() { return groupDetails; }

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
