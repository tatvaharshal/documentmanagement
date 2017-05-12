package com.tatvacoconet.controller;

/**
 * Created by pca48 on 5/11/2017.
 */
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.entity.DocumentStatus;
import com.tatvacoconet.entity.DocumentType;
import com.tatvacoconet.entity.VerticalData;
@Component
public class DocumentValidator implements Validator{
    Date date=new Date();
    String prefix="=,/,*";
    @Override
    public boolean supports(Class<?> aClass) {
        return DocumentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        DocumentDTO documentDTO = (DocumentDTO) obj;

        Date creationDate=documentDTO.getCreationDate();
        Date importDate=documentDTO.getImportDate();
        Date validFrom=documentDTO.getValidFrom();
        Date validTo=documentDTO.getValidTo();
        String documentName=documentDTO.getDocumentName();
        String documentDescription=documentDTO.getDocumentDescription();
        DocumentStatus documentStatus=documentDTO.getDocumentStatus();
        String documentTag=documentDTO.getDocumentTag();
        DocumentType documentType=documentDTO.getDocumentType();
       // VerticalData vertical_data=documentDTO.getVerticalData();
        //long file_size =multipartFile.getSize();



        if(documentName==null)
        {
            errors.reject("documentName", "errors.documentName");
        }


        if ((!documentDescription.equals("application/pdf"))) {

            errors.reject("type", "errors.documentDescription");
        }



        if(creationDate.before(date))
        {
            errors.reject("creationDate", "errors.creationDate");
        }
        if(importDate.before(date))
        {
            errors.reject("importDate", "errors.importDate");
        }
        if(validFrom.before(creationDate))
        {
            errors.reject("validFrom","errors.validFrom");
        }
        if(validTo.before(validFrom))
        {
            errors.reject("validTo","errors.validTo");
        }

        if(documentStatus==null)
        {
            errors.reject("documentStatus","errors.documentStatus");
        }
        if(documentTag==null)
        {
            errors.reject("documentTag","errors.documentTag");
        }
        if(documentType==null)
        {
            errors.reject("documentType","errors.documentType");
        }
       /* if(verticalData==null)
        {
            errors.reject("verticalData","errors.verticalData");
        }*/

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Document_Status.documentStatus",
                "error.documentStatus", "Required field");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentTag",
                "error.documentTag", "Required field");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentType",
                "error.documentType", "Required field");

      /*  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vertical_data",
                "error.vertical_data", "Required field");*/


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentName",
                "error.documentName", "Required field");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "documentDescription",
                "error.documentDescription", "Required field");

        if(documentName.startsWith(prefix))
        {
            errors.reject("documentName","errors.documentName");
        }

        if(documentDescription.startsWith(prefix))
        {
            errors.reject("documentDescription","errors.documentDescription");
        }

        }

}
