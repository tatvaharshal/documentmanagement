package com.tatvacoconet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tatvacoconet.entity.DocumentData;
import com.tatvacoconet.repository.DocumentDataRepository;
import com.tatvacoconet.service.DocumentDataService;

@Service
public class DocumentDataServiceImpl implements DocumentDataService {

    @Autowired
    private DocumentDataRepository dataRepository;

    @Override
    public DocumentData RegisterOrUpdate(DocumentData document) {
        return dataRepository.save(document);
    }
    @Override
    public List<DocumentData> getAllDocs() {
        return dataRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "documentId");
    }
    @Override
    public void updateDocument(DocumentData document) {
        dataRepository.save(document);
    }
    @Override
    public void delete(long documentId) {
        dataRepository.delete(documentId);
    }
    @Override
    public DocumentData getDocumentByID(long documentId) {
        return dataRepository.findOne(documentId);
    }
}