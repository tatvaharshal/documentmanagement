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
    String prefix="/^[a-zA-Z0-9]*$/";
    final int permitedSize = 5120;  //~ 5 MB in bytes

    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();
    public List<FieldErrorDTO> validateDocument(Object obj) {
        DocumentDTO documentDTO = (DocumentDTO) obj;

        if (documentDTO.getCreationDate().after(new Date())) {
            addFieldError("creationDate","Creation date is empty Or Future date is not allowed(<=today's date)");
        }
        if (documentDTO.getCreationDate()==null && documentDTO.getValidFrom()!=null) {
            addFieldError("validFrom","Enter creationDate First");
        }
        if (documentDTO.getValidFrom().before(new Date())) {
            addFieldError("validFrom","Past date for ValidFrom is not allowed(>=today's date)");
        }
        if (documentDTO.getCreationDate()==null && documentDTO.getValidFrom()==null && documentDTO.getValidFrom()!=null) {
            addFieldError("validTo","Enter creationDate and ValidFrom Date First ");
        }
        if (documentDTO.getValidTo().before(documentDTO.getValidFrom())) {
            addFieldError("validTo","ValidTo date is empty Or Past date is not allowed(>=valid_from date)");
        }
        if (documentDTO.getDocumentStatus()!=DocumentStatus.ForYourInformation) {
            addFieldError("documentStatus","DocumentStatus can not be blank");
        }
        if(documentDTO.getDocumentTag()!=null){
            String tag[]= documentDTO.getDocumentTag().split(",");
            List<String> doctagEnums = Stream.of(DocumentTag.values())
                    .map(DocumentTag::name).collect(Collectors.toList());
            for(String tagdetails:tag)
                if (!doctagEnums.contains(tagdetails)){
                    addFieldError("documentTag","documentTag can not be blank Or Select proper from option");
                }
        }
        if (documentDTO.getDocumentType() == null &&
                !documentDTO.getDocumentType().name().equals(DocumentType.Account_Statement.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Contract.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Information.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Offer.name())){
            addFieldError("documentType","DocumentType can not be blank Or Select proper from option");
        }
        if (!documentDTO.getAddressScope().name().equals(AddressScope.Group.name()) &&
                !documentDTO.getAddressScope().name().equals(AddressScope.Role.name())&&
                !documentDTO.getAddressScope().name().equals(AddressScope.UserId.name()) &&
                !documentDTO.getAddressScope().name().equals(AddressScope.None.name())) {
            addFieldError("addressScope","addressScope can not be blank Or Select proper from option");
        }
        if(documentDTO.getDocumentName()!=null){
            if (documentDTO.getDocumentName().startsWith(prefix)) {
                addFieldError("documentName","DocumentName should be proper");
            }
            if (documentDTO.getDocumentName().length()>25) {
                addFieldError("documentName","DocumentName length should be less than 255");
            }
        }
        if(documentDTO.getDocumentDescription()!=null){
            if (documentDTO.getDocumentDescription().startsWith(prefix)) {
                addFieldError("documentDescription","DocumentDescription  should be proper");
            }
            if (documentDTO.getDocumentDescription().length()>1000) {
                addFieldError("documentDescription","DocumentDescription  length should be less than 1000");
            }
        }
        return fieldErrors;
    }
    public List<FieldErrorDTO> validateFileDetails(Object obj) {
        DocumentDTO documentDTO = (DocumentDTO) obj;
        if(documentDTO==null){
            addFieldError("documentDTO","File Upload is mandatory");
        }
        if (documentDTO.getDocumentName().startsWith(prefix)) {
            addFieldError("documentName","DocumentName should be proper");
        }
        if (documentDTO.getDocumentName().length()>25) {
            addFieldError("documentName","DocumentName length should be less than 255");
        }
        if (documentDTO.getDocumentDescription().startsWith(prefix)) {
            addFieldError("documentDescription","DocumentDescription  should be proper");
        }
        if (documentDTO.getDocumentDescription().length()>1000) {
            addFieldError("documentDescription","DocumentDescription  length should be less than 1000");
        }
       /* if (!documentDTO.getDocumentDescription().equals("application/pdf")) {
            addFieldError("documentDescription","DocumentDescription should be pdf for DocumentDescription");
        }*/
        if (documentDTO.getFileSize()> permitedSize) {
            addFieldError("fileSize","FileSize is not allowed more than 5MB");
        }
        return fieldErrors;
    }
    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }
    public List<FieldErrorDTO> validateUpdateDocument(Object obj) {
        DocumentDTO documentUpdateDTO = (DocumentDTO) obj;

        if(documentUpdateDTO.getAddressScope()==null){
            if (!documentUpdateDTO.getAddressScope().name().equals(AddressScope.Group.name()) &&
                    !documentUpdateDTO.getAddressScope().name().equals(AddressScope.Role.name())&&
                    !documentUpdateDTO.getAddressScope().name().equals(AddressScope.UserId.name()) &&
                    !documentUpdateDTO.getAddressScope().name().equals(AddressScope.None.name())) {
                addFieldError("addressScope","addressScope can not be blank Or Select proper from option");
            }
        }
        if (documentUpdateDTO.getDocumentType() == null &&
                !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Account_Statement.name())&&
                !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Contract.name())&&
                !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Information.name())&&
                !documentUpdateDTO.getDocumentType().name().equals(DocumentType.Offer.name())){
            addFieldError("documentType","DocumentType can not be blank Or Select proper from option");
        }
        if (documentUpdateDTO.getDocumentStatus()!=DocumentStatus.ForYourInformation) {
            addFieldError("documentStatus","DocumentStatus can not be blank");
        }
        if (documentUpdateDTO.getValidFrom().before(new Date())) {
            addFieldError("validFrom","Past date for ValidFrom is not allowed(>=today's date)");
        }
        if (documentUpdateDTO.getValidTo().before(documentUpdateDTO.getValidFrom())) {
            addFieldError("validTo","ValidTo date is empty Or Past date is not allowed(>=valid_from date)");
        }

        if(documentUpdateDTO.getDocumentTag()!=null){
            String tag[]= documentUpdateDTO.getDocumentTag().split(",");
            List<String> doctagEnums = Stream.of(DocumentTag.values())
                    .map(DocumentTag::name).collect(Collectors.toList());
            for(String tagdetails:tag)
                if (!doctagEnums.contains(tagdetails)){
                    addFieldError("documentTag","documentTag can not be blank Or Select proper from option");
                }
            if(documentUpdateDTO.getDocumentName()!=null){
                if (documentUpdateDTO.getDocumentName().startsWith(prefix)) {
                    addFieldError("documentName","DocumentName should be proper");
                }
                if (documentUpdateDTO.getDocumentName().length()>25) {
                    addFieldError("documentName","DocumentName length should be less than 255");
                }
            }
            if(documentUpdateDTO.getDocumentDescription()!=null){
                if (documentUpdateDTO.getDocumentDescription().startsWith(prefix)) {
                    addFieldError("documentDescription","DocumentDescription  should be proper");
                }
                if (documentUpdateDTO.getDocumentDescription().length()>1000) {
                    addFieldError("documentDescription","DocumentDescription  length should be less than 1000");
                }
            }
        }
        if(documentUpdateDTO.getAddressScope()!=null){
            if (documentUpdateDTO.getDocumentStatus()!=DocumentStatus.ForYourInformation) {
                addFieldError("documentStatus","DocumentStatus can not be blank");
            }
            if (documentUpdateDTO.getValidFrom().before(new Date())) {
                addFieldError("validFrom","Past date for ValidFrom is not allowed(>=today's date)");
            }
            if (documentUpdateDTO.getValidTo().before(documentUpdateDTO.getValidFrom())) {
                addFieldError("validTo","ValidTo date is empty Or Past date is not allowed(>=valid_from date)");
            }

            if(documentUpdateDTO.getDocumentTag()!=null){
                String tag[]= documentUpdateDTO.getDocumentTag().split(",");
                List<String> doctagEnums = Stream.of(DocumentTag.values())
                        .map(DocumentTag::name).collect(Collectors.toList());
                for(String tagdetails:tag)
                    if (!doctagEnums.contains(tagdetails)){
                        addFieldError("documentTag","documentTag can not be blank Or Select proper from option");
                    }
            }
            if(documentUpdateDTO.getDocumentDescription()!=null){
                if (documentUpdateDTO.getDocumentDescription().startsWith(prefix)) {
                    addFieldError("documentDescription","DocumentDescription  should be proper");
                }
                if (documentUpdateDTO.getDocumentDescription().length()>1000) {
                    addFieldError("documentDescription","DocumentDescription  length should be less than 1000");
                }
            }
        }
        return fieldErrors;
    }
}