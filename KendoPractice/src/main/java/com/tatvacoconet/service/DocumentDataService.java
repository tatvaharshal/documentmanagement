package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentData;

public interface DocumentDataService {

    public void RegisterOrUpdate(DocumentData document);

    DocumentData getDocumentByID(long documentId);

    public List<DocumentData> getAllDocs();

}
