package com.tatvacoconet.service;

import java.util.List;

import com.tatvacoconet.entity.DocumentLink;

public interface DocumentLinkService {


    void addDocument(DocumentLink documentlink);

    List<DocumentLink> getAllDocumentsLink(long documentId);


}
