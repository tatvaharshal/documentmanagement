package com.tatvacoconet.dto;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.entity.DocumentLink;

import java.util.List;

/**
 * Created by pca48 on 5/3/2017.
 */
public class DocumentDTO {

    private DocumentData documentData;
    private List<DocumentLink> documentLink;

    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }

    public List<DocumentLink> getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(List<DocumentLink> documentLink) {
        this.documentLink = documentLink;
    }
}
