package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentLink;

public interface DocumentLinkService {

    void addDocument(DocumentLink documentLink);

    List<DocumentLink> getAllDocumentsLink(long documentId);

    void updateDocumentLink(DocumentLink documentLink);

    void deleteAll(long documentId);
}
