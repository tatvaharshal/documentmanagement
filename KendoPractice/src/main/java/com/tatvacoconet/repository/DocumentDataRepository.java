package com.tatvacoconet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatvacoconet.entity.DocumentData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDataRepository extends JpaRepository<DocumentData, Long>{

    /*@Query( "select creationDate,documentDescription,documentName,documentStatus,documentTag,documentType,fileSize,validFrom,validTo,verticalData,importDate,addressScope from DocumentData where documentId=:documentId")
    List<DocumentData> findByID(@Param("documentId") long documentId);*/

    @Query( "select n from DocumentData n where n.documentId in:documentId")
    List<DocumentData> findByID(@Param("documentId")long documentId);


}
