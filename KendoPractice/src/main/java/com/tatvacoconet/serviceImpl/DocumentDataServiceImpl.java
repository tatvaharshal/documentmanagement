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

    @Override
    public void RegisterOrUpdate(DocumentData document) {
        dataRepository.save(document);

    }

    @Override
    public List<DocumentData> getAllDocs() {

        return dataRepository.findAll();
    }
    @Override
    public void updateDocument(DocumentData document) {
        dataRepository.save(document);

    }

/*	@Override
	public List<Document_Data> getDocumentByID(long document_id) {

		 return dataRepository.findByID(document_id);
	}*/

    @Override
    public void delete(long documentId) {
        dataRepository.delete(documentId);
    }

    @Override
    public DocumentData getDocumentByID(long documentId) {
        return dataRepository.findOne(documentId);
    }



}
