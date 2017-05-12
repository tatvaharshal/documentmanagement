package com.tatvacoconet.controller;

/**
 * Created by pca48 on 5/11/2017.
 */
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tatvacoconet.dto.DocumentLinkDTO;
@Component
public class DocumentLinkValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return DocumentLinkDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "groupDetails",
                "error.groupDetails", "Required field");*/

        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleDetails",
                "error.roleDetails", "Required field");*/

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressScope",
                "error.addressScope", "Required field");

      /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId",
                "error.userId", "Required field");*/
    }

}
