package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentLink;

public interface DocumentLinkService {

    DocumentLink addDocument(DocumentLink documentLink);

    List<DocumentLink> getAllDocumentsLink(long documentId);

    void deleteAll(long documentId);

    DocumentLink getAllDocumentsById(long documentId);
}