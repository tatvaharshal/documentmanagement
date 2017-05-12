package com.tatvacoconet.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DocumentLink")
public class DocumentLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "documentLinkId", unique = true, nullable = false)
    private long documentLinkId;

    @Column(name = "userId")
    private long userId;


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
