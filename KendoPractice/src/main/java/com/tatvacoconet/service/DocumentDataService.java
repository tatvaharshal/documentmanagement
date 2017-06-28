package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentData;

public interface DocumentDataService {

    DocumentData RegisterOrUpdate(DocumentData document);

    List<DocumentData> getAllDocs();

    void updateDocument(DocumentData document);

    DocumentData getDocumentByID(long documentId);

    void delete(long documentId);

}
