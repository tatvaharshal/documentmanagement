package com.tatvacoconet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.repository.DocumentDataRepository;
import com.tatvacoconet.repository.DocumentLinkRepository;
import com.tatvacoconet.service.DocumentDataService;

@Service
public class DocumentDataServiceImpl implements DocumentDataService {
    @Autowired
    private DocumentDataRepository dataRepository;
    @Autowired
    private DocumentLinkRepository linkRepository;

    @Override
    public void RegisterOrUpdate(DocumentData document) {
        dataRepository.save(document);

    }

    @Override
    public List<DocumentData> getAllDocs() {

        return dataRepository.findAll();
    }

    @Override
    public DocumentData getDocumentByID(long documentId) {

        return dataRepository.findOne(documentId);
    }

}
