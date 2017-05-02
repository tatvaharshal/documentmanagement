package com.tatvacoconet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tatvacoconet.entity.DocumentLink;

public interface DocumentLinkRepository extends JpaRepository<DocumentLink, Long>{

    //@Query("Select documentLinkId,firstName,groupDetails,lastName,roleDetails,userId from DocumentLink where documentData_documentId=documentData_documentId")
    @Query( "select documentLinkId,firstName,groupDetails,lastName,roleDetails,userId from DocumentLink where documentData_documentId in :documentId")
    List<DocumentLink> findByID(@Param("documentId")long documentId);



}
