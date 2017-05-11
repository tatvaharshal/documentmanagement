package com.tatvacoconet.repository;

import java.util.List;

import com.tatvacoconet.entity.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tatvacoconet.entity.DocumentLink;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentLinkRepository extends JpaRepository<DocumentLink, Long>{

    //@Query("Select documentLinkId,firstName,groupDetails,lastName,roleDetails,userId from DocumentLink where documentData_documentId=documentData_documentId")
  /*  @Query("select documentLinkId,groupDetails,roleDetails,userId from DocumentLink where documentData.documentId in:documentId")
    List<DocumentLink> findByID(@Param("documentId") long documentId);*/
   // List<DocumentLink> findByDocumentID(DocumentData documentData);
   @Query( "select n from DocumentLink n where n.documentData.documentId =:documentId")
   List<DocumentLink> findByID(@Param("documentId")long documentId);

}
