package com.tatvacoconet.controller;

/**
 * Created by pca48 on 5/11/2017.
 */
import java.util.Date;

import com.tatvacoconet.entity.AddressScope;
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
    final int permitedSize = 5242880;  //~ 5 MB in bytes
    @Override
    public boolean supports(Class<?> aClass) {
        return DocumentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        DocumentDTO documentDTO = (DocumentDTO) obj;

        // Vertical_Data vertical_data=documentDTO.getVertical_data();
        //long file_size =multipartFile.getSize();
        if (documentDTO== null) {
            errors.rejectValue("documentDTO", "error.documentDTO");
        }
        if (documentDTO.getCreationDate() == null|| documentDTO.getCreationDate().after(new Date())) {
            errors.rejectValue("creationDate", "error.creationDate");

        }
      /*  if (documentDTO.getImportDate().before(new Date())) {
            errors.rejectValue("importDate", "error.importDate");
        }*/
        if (documentDTO.getValidFrom() == null|| documentDTO.getValidFrom().before(documentDTO.getCreationDate())) {
            errors.rejectValue("validFrom", "error.validFrom");
        }
        if (documentDTO.getValidTo() == null|| documentDTO.getValidTo().before(documentDTO.getValidFrom())) {
            errors.rejectValue("validTo", "error.validTo");
        }
        if (documentDTO.getDocumentStatus() == null||documentDTO.getDocumentStatus()!=DocumentStatus.ForYourInformation) {
            errors.rejectValue("documentStatus", "error.documentStatus");
        }
        if (documentDTO.getDocumentTag() == null) {
            errors.rejectValue("documentTag", "error.documentTag");
        }
        if (documentDTO.getDocumentType() == null&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Account_Statement.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Contract.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Information.name())&&
                !documentDTO.getDocumentType().name().equals(DocumentType.Offer.name())){
            errors.rejectValue("documentType", "error.documentType");
        }

        if (documentDTO.getAddressScope() == null&&
                !documentDTO.getAddressScope().name().equals(AddressScope.Group.name()) &&
                !documentDTO.getAddressScope().name().equals(AddressScope.Role.name())&&
                !documentDTO.getAddressScope().name().equals(AddressScope.UserId.name()) &&
                !documentDTO.getAddressScope().name().equals(AddressScope.None.name())) {
            errors.rejectValue("addressScope", "error.addressScope");
        }

    }
    public void validateFileDetails(Object obj, Errors errors) {
        DocumentDTO documentDTO = (DocumentDTO) obj;

        if (documentDTO.getDocumentName().startsWith(prefix)) {
            errors.rejectValue("documentName", "error.documentName");
        }
        if (documentDTO.getDocumentDescription().startsWith(prefix)) {
            errors.rejectValue("documentDescription", "error.documentDescription");
        }

        if (documentDTO.getFileSize()> permitedSize) {
            errors.rejectValue("fileSize", "error.fileSize");
        }


    }


}
