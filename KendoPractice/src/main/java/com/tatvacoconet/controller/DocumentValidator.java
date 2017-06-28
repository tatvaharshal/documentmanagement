package com.tatvacoconet.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tatvacoconet.entity.*;
import org.springframework.stereotype.Component;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.FieldErrorDTO;

@Component
public class DocumentValidator{
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    String currentDate=dateFormat.format(date);

    //String prefix="^[a-zA-Z0-9-.\\s]*$";
    String prefix="^[a-zA-Z0-9]+(([.-][a-zA-Z0-9])?[a-zA-Z0-9\\s]*)*$";
    final int permitedSize = 5120;  //~ 5 MB in bytes

    public List<FieldErrorDTO> validateDocument(Object obj) throws ParseException {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentDTO = (DocumentDTO) obj;
        if(documentDTO.getValidFrom()!=null){
            String validFrom=dateFormat.format(documentDTO.getValidFrom());
            Date validFromDate=new SimpleDateFormat("yyyy/MM/dd").parse(validFrom);
            documentDTO.setValidFrom(validFromDate);
        }

        if(documentDTO.getValidTo()!=null){
            String vaidTo=dateFormat.format(documentDTO.getValidTo());
            Date vaidToDate=new SimpleDateFormat("yyyy/MM/dd").parse(vaidTo);
            documentDTO.setValidTo(vaidToDate);
        }

        if(documentDTO.getCreationDate()!=null){
            String creationDate=dateFormat.format(documentDTO.getCreationDate());
            Date creationDateData=new SimpleDateFormat("yyyy/MM/dd").parse(creationDate);
            documentDTO.setCreationDate(creationDateData);
        }

        Date todayDate=new SimpleDateFormat("yyyy/MM/dd").parse(currentDate);

        if(documentDTO.getCreationDate()!=null){
            if (documentDTO.getCreationDate().after(todayDate)) {
                fieldErrors.add(new FieldErrorDTO("creationDate",
                        "Future date is not allowed(<=today's date)"));
            }
        }
        if(documentDTO.getValidFrom()!=null){
            if (documentDTO.getValidFrom().before(todayDate)) {
                fieldErrors.add(new FieldErrorDTO("validFrom",
                        "Past date for ValidFrom is not allowed(>=today's date)"));
            }
        }
        if(documentDTO.getValidTo()!=null){
            if (documentDTO.getValidFrom()==null) {
                fieldErrors.add(new FieldErrorDTO("validTo",
                        "Enter ValidFrom Date First"));
            }
            if (documentDTO.getValidTo().before(documentDTO.getValidFrom())) {
                fieldErrors.add(new FieldErrorDTO("validTo",
                        "Past date is not allowed(>=valid_from date)"));
            }
        }
        if(documentDTO.getDocumentStatus()!=null && documentDTO.getDocumentStatus()!=""){
            String docStatus= documentDTO.getDocumentStatus();
            List<String> docStatusEnums = Stream.of(DocumentStatus.values())
                    .map(DocumentStatus::name).collect(Collectors.toList());
            if (!docStatusEnums.contains(docStatus)){
                fieldErrors.add(new FieldErrorDTO("documentStatus","Select proper from option"));
            }
        }
        if(documentDTO.getDocumentTag() != null && documentDTO.getDocumentTag()!=""){
            String tag[]= documentDTO.getDocumentTag().split(",");
            List<String> doctagEnums = Stream.of(DocumentTag.values())
                    .map(DocumentTag::name).collect(Collectors.toList());
            for(String tagdetails:tag)
                if (!doctagEnums.contains(tagdetails)){
                    fieldErrors.add(new FieldErrorDTO("documentTag",
                            "Document Tag can not be blank Or Select proper from option"));
                }
        }
        if (documentDTO.getDocumentType().equals(DocumentType.None.name())){
            fieldErrors.add(new FieldErrorDTO("documentType","Document Type can not be blank"));
        }
        if(documentDTO.getDocumentType()==null){
            fieldErrors.add(new FieldErrorDTO("documentType","Document Type can not be blank"));
        }
        if(documentDTO.getDocumentType()!=null){
            String docType= documentDTO.getDocumentType();
            List<String> docTypeEnums = Stream.of(DocumentType.values())
                    .map(DocumentType::name).collect(Collectors.toList());
            if (!docTypeEnums.contains(docType)){
                fieldErrors.add(new FieldErrorDTO("Document Type","Select proper from option"));
            }
        }
        if(documentDTO.getAddressScope()!=null){
            if (!documentDTO.getAddressScope().name().equals(AddressScope.Group.name()) &&
                    !documentDTO.getAddressScope().name().equals(AddressScope.Role.name())&&
                    !documentDTO.getAddressScope().name().equals(AddressScope.UserId.name()) &&
                    !documentDTO.getAddressScope().name().equals(AddressScope.None.name())) {
                fieldErrors.add(new FieldErrorDTO("addressScope","Select proper from option"));
            }
            if(!documentDTO.getAddressScope().name().equals(AddressScope.None.name()) && documentDTO.getDocumentLinkDTO()==null){
                fieldErrors.add(new FieldErrorDTO("addressScope",
                        "Select proper group or Role or UserId from option"));

            }
        }
        if(documentDTO.getDocumentDescription()!=null){
            if (!documentDTO.getDocumentDescription().matches(prefix)|| documentDTO.getDocumentDescription()=="") {
                fieldErrors.add(new FieldErrorDTO("documentDescription",
                        "Enter Valid Document Description using A-Z,a-z,0-9,.,- characters only"));
            }
            if (documentDTO.getDocumentDescription().length()>1000) {
                fieldErrors.add(new FieldErrorDTO("documentDescription",
                        "Document Description length should be less than 1000"));
            }
        }
        if(documentDTO.getDocumentName()!=null){
            if (!documentDTO.getDocumentName().matches(prefix)|| documentDTO.getDocumentName()=="") {
                fieldErrors.add(new FieldErrorDTO("documentName","Enter Valid Document Name using A-Z,a-z,0-9,.,- characters only"));
            }
            if (documentDTO.getDocumentName().length()>255) {
                fieldErrors.add(new FieldErrorDTO("documentName",
                        "Document Name length should be less than 255"));
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
        if (!documentDTO.getDocumentName().matches(prefix)|| documentDTO.getDocumentName()=="") {
            fieldErrors.add(new FieldErrorDTO("documentName", "Enter Valid Document Name using A-Z,a-z,0-9,.,- characters only"));
        }
        if (documentDTO.getDocumentName().length() > 255) {
            fieldErrors.add(new FieldErrorDTO("documentName",
                    "Document Name length should be less than 255"));
        }
        if (documentDTO.getFileSize() > permitedSize) {
            fieldErrors.add(new FieldErrorDTO("fileSize",
                    "File Size is not allowed more than 5MB"));
        }
        return fieldErrors;
    }

    public List<FieldErrorDTO> validateUpdateDocument(Object obj) throws ParseException {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentDTO documentUpdateDTO = (DocumentDTO) obj;

        String importDate=dateFormat.format(documentUpdateDTO.getImportDate());

        if(documentUpdateDTO.getValidFrom()!=null){
            String validFrom=dateFormat.format(documentUpdateDTO.getValidFrom());
            Date validFromDate=new SimpleDateFormat("yyyy/MM/dd").parse(validFrom);
            documentUpdateDTO.setValidFrom(validFromDate);
        }

        if(documentUpdateDTO.getValidTo()!=null){
            String vaidTo=dateFormat.format(documentUpdateDTO.getValidTo());
            Date vaidToDate=new SimpleDateFormat("yyyy/MM/dd").parse(vaidTo);
            documentUpdateDTO.setValidTo(vaidToDate);
        }

        Date importDateData=new SimpleDateFormat("yyyy/MM/dd").parse(importDate);
        // Date todayDate=new SimpleDateFormat("yyyy/MM/dd").parse(currentDate);

        if (documentUpdateDTO.getAddressScope() != null) {
            if (!documentUpdateDTO.getAddressScope().name().equals(AddressScope.Group.name())
                    && !documentUpdateDTO.getAddressScope().name().equals(AddressScope.Role.name())
                    && !documentUpdateDTO.getAddressScope().name().equals(AddressScope.UserId.name())
                    && !documentUpdateDTO.getAddressScope().name().equals(AddressScope.None.name())) {
                fieldErrors.add(new FieldErrorDTO("addressScope",
                        "addressScope can not be blank Or Select proper from option"));
            }
            if(!documentUpdateDTO.getAddressScope().name().equals(AddressScope.None.name())
                    && (documentUpdateDTO.getDocumentLinkDTO().getRoleDetails()==null
                    && documentUpdateDTO.getDocumentLinkDTO().getGroupDetails()==null
                    && documentUpdateDTO.getDocumentLinkDTO().getUserId()==null)){
                fieldErrors.add(new FieldErrorDTO("addressScope",
                        "Select proper group or Role or UserId from option"));

            }
        }
        if (documentUpdateDTO.getDocumentType().equals(DocumentType.None.name())){
            fieldErrors.add(new FieldErrorDTO("documentType","Document Type can not be blank"));
        }
        if(documentUpdateDTO.getDocumentType()==null){
            fieldErrors.add(new FieldErrorDTO("documentType","Document Type can not be blank"));
        }
        if(documentUpdateDTO.getDocumentType()!=null){
            String docType= documentUpdateDTO.getDocumentType();
            List<String> docTypeEnums = Stream.of(DocumentType.values())
                    .map(DocumentType::name).collect(Collectors.toList());
            if (!docTypeEnums.contains(docType)){
                fieldErrors.add(new FieldErrorDTO("documentType","Select proper from option"));
            }
        }
        if(documentUpdateDTO.getDocumentStatus()!=null && documentUpdateDTO.getDocumentStatus()!="" ){
            String docStatus= documentUpdateDTO.getDocumentStatus();
            List<String> docStatusEnums = Stream.of(DocumentStatus.values())
                    .map(DocumentStatus::name).collect(Collectors.toList());
            if (!docStatusEnums.contains(docStatus)){
                fieldErrors.add(new FieldErrorDTO("documentStatus","Select proper from option"));
            }
        }
        if(documentUpdateDTO.getValidFrom()!=null){
            if (documentUpdateDTO.getValidFrom().before(importDateData)){
                fieldErrors.add(new FieldErrorDTO("validFrom",
                        "Past date for ValidFrom is not allowed(>=importDate)"));
            }
        }
        if (documentUpdateDTO.getValidTo()!=null){
            if (documentUpdateDTO.getValidFrom()==null) {
                fieldErrors.add(new FieldErrorDTO("validTo",
                        "Enter ValidFrom Date First"));
            }
            if (documentUpdateDTO.getValidTo().before(documentUpdateDTO.getValidFrom())) {
                fieldErrors.add(new FieldErrorDTO("validTo",
                        "Past date is not allowed(>=valid_from date)"));
            }
        }
        if (documentUpdateDTO.getDocumentTag() != null && documentUpdateDTO.getDocumentTag()!="") {
            String tag[] = documentUpdateDTO.getDocumentTag().split(",");
            List<String> doctagEnums = Stream.of(DocumentTag.values())
                    .map(DocumentTag::name).collect(Collectors.toList());
            for (String tagdetails : tag)
                if (!doctagEnums.contains(tagdetails)) {
                    fieldErrors.add(new FieldErrorDTO("documentTag",
                            "Document Tag can not be blank Or Select proper from option"));
                }
        }
        if (documentUpdateDTO.getDocumentDescription() != null) {
            if (!documentUpdateDTO.getDocumentDescription().matches(prefix)|| documentUpdateDTO.getDocumentDescription()=="") {
                fieldErrors.add(new FieldErrorDTO("documentDescription",
                        "Enter Valid Document Description using A-Z,a-z,0-9,- characters only"));
            }
            if (documentUpdateDTO.getDocumentDescription().length() > 1000) {
                fieldErrors.add(new FieldErrorDTO("documentDescription",
                        "Document Description  length should be less than 1000"));
            }
        }
        if (documentUpdateDTO.getAddressScope() == null) {

            if(documentUpdateDTO.getDocumentStatus()!=null && documentUpdateDTO.getDocumentStatus()!=""){
                String docStatus= documentUpdateDTO.getDocumentStatus();
                List<String> docStatusEnums = Stream.of(DocumentStatus.values())
                        .map(DocumentStatus::name).collect(Collectors.toList());
                if (!docStatusEnums.contains(docStatus)){
                    fieldErrors.add(new FieldErrorDTO("documentStatus","Select proper from option"));
                }
            }
            if (documentUpdateDTO.getValidFrom()!=null){
                if (documentUpdateDTO.getValidFrom().before(importDateData)){
                    fieldErrors.add(new FieldErrorDTO("validFrom",
                            "Past date for ValidFrom is not allowed(>=importDate)"));
                }
            }
            if (documentUpdateDTO.getValidTo()!=null){
                if (documentUpdateDTO.getValidFrom()==null) {
                    fieldErrors.add(new FieldErrorDTO("validTo",
                            "Enter ValidFrom Date First"));
                }
                if (documentUpdateDTO.getValidTo().before(documentUpdateDTO.getValidFrom())) {
                    fieldErrors.add(new FieldErrorDTO("validTo",
                            "Past date is not allowed(>=valid_from date)"));
                }
            }
            if (documentUpdateDTO.getDocumentTag() != null && documentUpdateDTO.getDocumentTag()!="") {
                String tag[] = documentUpdateDTO.getDocumentTag().split(",");
                List<String> doctagEnums = Stream.of(DocumentTag.values())
                        .map(DocumentTag::name).collect(Collectors.toList());
                for (String tagdetails : tag)
                    if (!doctagEnums.contains(tagdetails)) {
                        fieldErrors.add(new FieldErrorDTO("documentTag",
                                "Document Tag can not be blank Or Select proper from option"));
                    }
            }
            if (documentUpdateDTO.getDocumentDescription() != null) {
                if (!documentUpdateDTO.getDocumentDescription().matches(prefix)|| documentUpdateDTO.getDocumentDescription()=="") {
                    fieldErrors.add(new FieldErrorDTO("documentDescription",
                            "Enter Valid Document Description using A-Z,a-z,0-9,- characters only"));
                }
                if (documentUpdateDTO.getDocumentDescription().length() > 1000) {
                    fieldErrors.add(new FieldErrorDTO("documentDescription",
                            "Document Description  length should be less than 1000"));
                }
            }
            if(documentUpdateDTO.getDocumentName()==null){
                fieldErrors.add(new FieldErrorDTO("documentName","Enter Valid Document Name using A-Z,a-z,0-9,.,- characters only"));
            }
            if (documentUpdateDTO.getDocumentName() != null) {
                if (!documentUpdateDTO.getDocumentName().matches(prefix)|| documentUpdateDTO.getDocumentName()=="") {
                    fieldErrors.add(new FieldErrorDTO("documentName","Enter Valid Document Name using A-Z,a-z,0-9,.,- characters only"));
                }
                if (documentUpdateDTO.getDocumentName().length() > 255) {
                    fieldErrors.add(new FieldErrorDTO("documentName",
                            "Document Name length should be less than 255"));
                }
            }

        }
        return fieldErrors;
    }
}