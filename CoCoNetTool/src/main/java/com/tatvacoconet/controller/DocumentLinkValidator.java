package com.tatvacoconet.controller;

/**
 * Created by pca48 on 5/11/2017.
 */
import com.tatvacoconet.entity.UserId;
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
        DocumentLinkDTO documentLinkDTO = (DocumentLinkDTO) obj;

        if(documentLinkDTO==null){
            errors.reject("documentLinkDTO", "errors.documentLinkDTO");
        }
        if(documentLinkDTO.getRoleDetails()==null && documentLinkDTO.getUserId()==null&&
                !documentLinkDTO.getGroupDetails().equals("GermanyCards")&&
                !documentLinkDTO.getGroupDetails().equals("ItalyMULTIVERSAIFP")&&
                !documentLinkDTO.getGroupDetails().equals("FranceCards")&&
                !documentLinkDTO.getGroupDetails().equals("GrermanyMULTIVERSAIFP")&&
                !documentLinkDTO.getGroupDetails().equals("AustriaMULTIVERSAIFP")){
            errors.reject("groupDetails", "errors.groupDetails");
        }
        if(documentLinkDTO.getGroupDetails()==null&&documentLinkDTO.getUserId()==null&&
                !documentLinkDTO.getRoleDetails().equals("ALLUsers")&&
                !documentLinkDTO.getRoleDetails().equals("Attentionwidget")&&
                !documentLinkDTO.getRoleDetails().equals("Balancewidget")&&
                !documentLinkDTO.getRoleDetails().equals("Favouritewidget")&&
                !documentLinkDTO.getRoleDetails().equals("Liquiditywidget")&&
                !documentLinkDTO.getRoleDetails().equals("Paymentcreation")&&
                !documentLinkDTO.getRoleDetails().equals("Paymentwidget")){
            errors.reject("roleDetails", "errors.roleDetails");
        }
        if(documentLinkDTO.getGroupDetails()==null&&documentLinkDTO.getRoleDetails()==null&&
                !documentLinkDTO.getUserId().name().equals(UserId.Bhavin.name())&&
                !documentLinkDTO.getUserId().name().equals(UserId.Harshal.name())&&
                !documentLinkDTO.getUserId().name().equals(UserId.Savan.name())&&
                !documentLinkDTO.getUserId().name().equals(UserId.Vimal.name())){
            errors.reject("userId", "errors.userId");
        }

    }

}


