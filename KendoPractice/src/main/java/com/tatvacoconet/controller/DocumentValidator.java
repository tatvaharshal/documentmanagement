package com.tatvacoconet.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.FieldErrorDTO;
import com.tatvacoconet.entity.AddressScope;
import com.tatvacoconet.entity.DocumentStatus;
import com.tatvacoconet.entity.DocumentTag;
import com.tatvacoconet.entity.DocumentType;
@Component
public class DocumentValidator{
    Date date=new Date();
    String prefix="^[a-zA-Z0-9-.\\s]*$";
    final int permitedSize = 5120;  //~ 5 MB in bytes

    public List<FieldErrorDTO> validateDocument(Object obj) {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentDTO = (DocumentDTO) obj;
        if(documentDTO.getCreationDate()!=null){
            if (documentDTO.getCreationDate().after(new Date())) {
                fieldErrors.add(new FieldErrorDTO("creationDate","Creation date is empty Or Future date is not allowed(<=today's date)"));
            }
        }
        if(documentDTO.getValidFrom()!=null){
            if (documentDTO.getCreationDate()==null) {
                fieldErrors.add(new FieldErrorDTO("validFrom","Enter creationDate First"));
            }
            if (documentDTO.getValidFrom().before(new Date())) {
                fieldErrors.add(new FieldErrorDTO("validFrom","Past date for ValidFrom is not allowed(>=today's date)"));
            }
        }
        if(documentDTO.getValidTo()!=null){
            if (documentDTO.getCreationDate()==null && documentDTO.getValidFrom()==null) {
                fieldErrors.add(new FieldErrorDTO("validTo","Enter creationDate and ValidFrom Date First"));
            }
            if (documentDTO.getValidTo().before(documentDTO.getValidFrom())) {
                fieldErrors.add(new FieldErrorDTO("validTo","ValidTo date is empty Or Past date is not allowed(>=valid_from date)"));
            }
        }
        if(documentDTO.getDocumentStatus()!=null){
            if (documentDTO.getDocumentStatus()!=DocumentStatus.ForYourInformation) {
                fieldErrors.add(new FieldErrorDTO("documentStatus","DocumentStatus can not be blank"));
            }
        }
        if(documentDTO.getDocumentTag()!=null){
            String tag[]= documentDTO.getDocumentTag().split(",");
            List<String> doctagEnums = Stream.of(DocumentTag.values())
                    .map(DocumentTag::name).collect(Collectors.toList());
            for(String tagdetails:tag)
                if (!doctagEnums.contains(tagdetails)){
                    fieldErrors.add(new FieldErrorDTO("documentTag","documentTag can not be blank Or Select proper from option"));

                }
        }
        if (documentDTO.getDocumentType() == null &&
                !documentDTO.getDocumentType().name().equals(DocumentType.Account_Statement.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Contract.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Information.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Offer.name())){
            fieldErrors.add(new FieldErrorDTO("documentType","DocumentType can not be blank Or Select proper from option"));
        }
        if(documentDTO.getAddressScope()!=null){
            if (!documentDTO.getAddressScope().name().equals(AddressScope.Group.name()) &&
                    !documentDTO.getAddressScope().name().equals(AddressScope.Role.name())&&
                    !documentDTO.getAddressScope().name().equals(AddressScope.UserId.name()) &&
                    !documentDTO.getAddressScope().name().equals(AddressScope.None.name())) {
                fieldErrors.add(new FieldErrorDTO("addressScope","addressScope can not be blank Or Select proper from option"));
            }
        }
        if(documentDTO.getDocumentDescription()!=null){
            if (!documentDTO.getDocumentDescription().matches(prefix)) {
                fieldErrors.add(new FieldErrorDTO("documentDescription","DocumentDescription  should be proper"));
            }
            if (documentDTO.getDocumentDescription().length()>1000) {
                fieldErrors.add(new FieldErrorDTO("documentDescription","DocumentDescription  length should be less than 1000"));
            }
        }
        if(documentDTO.getDocumentName()!=null){
            if (!documentDTO.getDocumentName().matches(prefix)) {
                fieldErrors.add(new FieldErrorDTO("documentName","DocumentName should be proper"));
            }
            if (documentDTO.getDocumentName().length()>255) {
                fieldErrors.add(new FieldErrorDTO("documentName","DocumentName length should be less than 255"));
            }
        }
        return fieldErrors;
    }

    public List<FieldErrorDTO> validateFileDetails(Object obj) {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentDTO = (DocumentDTO) obj;
        if (documentDTO == null) {
            fieldErrors.add(new FieldErrorDTO("documentDTO", "File Upload is mandatory"));
        }
        if (!documentDTO.getDocumentName().matches(prefix)) {
            fieldErrors.add(new FieldErrorDTO("documentName", "DocumentName should be proper"));
        }
        if (documentDTO.getDocumentName().length() > 255) {
            fieldErrors.add(new FieldErrorDTO("documentName","DocumentName length should be less than 255"));
        }
        if (documentDTO.getFileSize() > permitedSize) {
            fieldErrors.add(new FieldErrorDTO("fileSize", "FileSize is not allowed more than 5MB"));
        }
        return fieldErrors;
    }

    public List<FieldErrorDTO> validateUpdateDocument(Object obj) {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentUpdateDTO = (DocumentDTO) obj;

        if (documentUpdateDTO.getAddressScope() != null) {

            if (!documentUpdateDTO.getAddressScope().name().equals(AddressScope.Group.name())
                    && !documentUpdateDTO.getAddressScope().name().equals(AddressScope.Role.name())
                    && !documentUpdateDTO.getAddressScope().name().equals(AddressScope.UserId.name())
                    && !documentUpdateDTO.getAddressScope().name().equals(AddressScope.None.name())) {
                fieldErrors.add(new FieldErrorDTO("addressScope","addressScope can not be blank Or Select proper from option"));
            }
        }
        if (documentUpdateDTO.getDocumentType() == null
                && !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Account_Statement.name())
                && !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Contract.name())
                && !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Information.name())
                && !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Offer.name())) {
            fieldErrors.add(new FieldErrorDTO("documentType","DocumentType can not be blank Or Select proper from option"));
        }
        if(documentUpdateDTO.getDocumentStatus()!=null){
            if (documentUpdateDTO.getDocumentStatus() != DocumentStatus.ForYourInformation) {
                fieldErrors.add(new FieldErrorDTO("documentStatus", "DocumentStatus can not be blank"));
            }
        }
        if (documentUpdateDTO.getValidFrom()!=null){
            if (documentUpdateDTO.getValidFrom().before(new Date())) {
                fieldErrors.add(new FieldErrorDTO("validFrom","Past date for ValidFrom is not allowed(>=today's date)"));
            }
        }
        if (documentUpdateDTO.getValidTo()!=null){
            if (documentUpdateDTO.getValidTo().before(documentUpdateDTO.getValidFrom())) {
                fieldErrors.add(new FieldErrorDTO("validTo","ValidTo date is empty Or Past date is not allowed(>=valid_from date)"));
            }
        }

        if (documentUpdateDTO.getDocumentTag() != null) {
            String tag[] = documentUpdateDTO.getDocumentTag().split(",");
            List<String> doctagEnums = Stream.of(DocumentTag.values())
                    .map(DocumentTag::name).collect(Collectors.toList());
            for (String tagdetails : tag)
                if (!doctagEnums.contains(tagdetails)) {
                    fieldErrors.add(new FieldErrorDTO("documentTag","documentTag can not be blank Or Select proper from option"));
                }

            if (documentUpdateDTO.getDocumentName() != null) {
                if (!documentUpdateDTO.getDocumentName().matches(prefix)) {
                    fieldErrors.add(new FieldErrorDTO("documentName","DocumentName should be proper"));
                }
                if (documentUpdateDTO.getDocumentName().length() > 255) {
                    fieldErrors.add(new FieldErrorDTO("documentName","DocumentName length should be less than 255"));
                }
            }
            if (documentUpdateDTO.getDocumentDescription() != null) {
                if (!documentUpdateDTO.getDocumentDescription().matches(prefix)) {
                    fieldErrors.add(new FieldErrorDTO("documentDescription","DocumentDescription  should be proper"));
                }
                if (documentUpdateDTO.getDocumentDescription().length() > 1000) {
                    fieldErrors.add(new FieldErrorDTO("documentDescription","DocumentDescription  length should be less than 1000"));
                }
            }
        }
        if (documentUpdateDTO.getAddressScope() == null) {
            if (documentUpdateDTO.getDocumentStatus()!=null){
                if (documentUpdateDTO.getDocumentStatus() != DocumentStatus.ForYourInformation) {
                    fieldErrors.add(new FieldErrorDTO("documentStatus","DocumentStatus can not be blank"));
                }
            }
            if (documentUpdateDTO.getValidFrom()!=null){
                if (documentUpdateDTO.getValidFrom().before(new Date())) {
                    fieldErrors.add(new FieldErrorDTO("validFrom","Past date for ValidFrom is not allowed(>=today's date)"));
                }
            }
            if (documentUpdateDTO.getValidTo()!=null){
                if (documentUpdateDTO.getValidTo().before(documentUpdateDTO.getValidFrom())) {
                    fieldErrors.add(new FieldErrorDTO("validTo","ValidTo date is empty Or Past date is not allowed(>=valid_from date"));
                }
            }

            if (documentUpdateDTO.getDocumentTag() != null) {
                String tag[] = documentUpdateDTO.getDocumentTag().split(",");
                List<String> doctagEnums = Stream.of(DocumentTag.values())
                        .map(DocumentTag::name).collect(Collectors.toList());
                for (String tagdetails : tag)
                    if (!doctagEnums.contains(tagdetails)) {
                        fieldErrors.add(new FieldErrorDTO("documentTag","documentTag can not be blank Or Select proper from option"));
                    }
            }
            if (documentUpdateDTO.getDocumentDescription() != null) {
                if (!documentUpdateDTO.getDocumentDescription().matches(prefix)) {
                    fieldErrors.add(new FieldErrorDTO("documentDescription","DocumentDescription  should be proper"));
                }
                if (documentUpdateDTO.getDocumentDescription().length() > 1000) {
                    fieldErrors.add(new FieldErrorDTO("documentDescription","DocumentDescription  length should be less than 1000"));
                }
            }
        }
        return fieldErrors;
    }
}