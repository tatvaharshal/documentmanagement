package com.tatvacoconet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tatvacoconet.entity.DocumentLink;

@Repository
public interface DocumentLinkRepository extends JpaRepository<DocumentLink, Long>{

    @Query( "select n from DocumentLink n where n.documentData.documentId =:documentId")
    List<DocumentLink> findByID(@Param("documentId")long documentId);

    @Query( "select n from DocumentLink n where n.documentData.documentId = :documentId")
    DocumentLink findById(@Param("documentId")long documentId);

}
