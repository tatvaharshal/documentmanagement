package com.tatvacoconet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tatvacoconet.entity.DocumentData;

@Repository
public interface DocumentDataRepository extends JpaRepository<DocumentData, Long>{
 /*   @Query( "select documentName from DocumentData  where documentName =:documentName")
    boolean existsDocument(String string,@Param("documentName")String documentName);*/

 /*   @Query( "select documentName from DocumentData  where documentName =:documentName")
    String existsDocument(@Param("documentName")String documentName);
*/

  /*  @Query( "select n from DocumentData n where n.documentId =:documentId")
    List<DocumentData> findByID(@Param("documentId")long documentId);
*/

}