package com.tatvacoconet.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvacoconet.entity.DocumentLink;
import com.tatvacoconet.repository.DocumentLinkRepository;
import com.tatvacoconet.service.DocumentLinkService;

@Service
public class DocumentLinkServiceImpl implements DocumentLinkService {
    @Autowired
    private DocumentLinkRepository linkRepository;

    @Override
    public List<DocumentLink> getAllDocumentsLink(long documentId) {
        return linkRepository.findByID(documentId);
    }
    @Override
    public DocumentLink addDocument(DocumentLink documentLink) {
        return linkRepository.save(documentLink);
    }

    @Override
    public void deleteAll(long documentId) {
        List<DocumentLink> list=linkRepository.findByID(documentId);
        linkRepository.delete(list);
    }
    @Override
    public DocumentLink getAllDocumentsById(long documentId) {
        return linkRepository.findById(documentId);
    }
}
