package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentData;

public interface DocumentDataService {

    public void RegisterOrUpdate(DocumentData document);

	/*Document_Data getDocumentByID(long document_id);*/

    public List<DocumentData> getAllDocs();

    public void updateDocument(DocumentData document);

    DocumentData getDocumentByID(long documentId);

    public void delete(long documentId);


}
