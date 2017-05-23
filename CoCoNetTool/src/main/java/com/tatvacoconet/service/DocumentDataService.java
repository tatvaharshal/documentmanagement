package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentData;

public interface DocumentDataService {

    public DocumentData RegisterOrUpdate(DocumentData document);

    public List<DocumentData> getAllDocs();

    public void updateDocument(DocumentData document);

    DocumentData getDocumentByID(long documentId);

    public void delete(long documentId);


}
