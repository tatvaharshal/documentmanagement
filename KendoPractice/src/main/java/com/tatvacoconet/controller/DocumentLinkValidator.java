package com.tatvacoconet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.tatvacoconet.dto.DocumentLinkDTO;
import com.tatvacoconet.dto.FieldErrorDTO;
import com.tatvacoconet.entity.GroupDetails;
import com.tatvacoconet.entity.RoleDetails;
import com.tatvacoconet.entity.UserId;

@Component
public class DocumentLinkValidator{

    public List<FieldErrorDTO> validateDocumentLink(Object obj) {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        DocumentLinkDTO documentLinkDTO = (DocumentLinkDTO) obj;
        if(documentLinkDTO.getGroupDetails()==null &&
                (documentLinkDTO.getRoleDetails()==null && documentLinkDTO.getUserId()==null)){
            fieldErrors.add(new FieldErrorDTO("groupDetails","groupDetails can not be blank"));
        }
        if(documentLinkDTO.getGroupDetails()!=null){
            String group[]= documentLinkDTO.getGroupDetails().split(",");
            List<String> groupEnums = Stream.of(GroupDetails.values())
                    .map(GroupDetails::name).collect(Collectors.toList());
            for(String groupdetails:group)
                if (!groupEnums.contains(groupdetails)){
                    fieldErrors.add(new FieldErrorDTO("groupDetails","Select proper groupDetails from option"));
                }
        }
        if(documentLinkDTO.getRoleDetails()==null &&
                (documentLinkDTO.getGroupDetails()==null && documentLinkDTO.getUserId()==null)){
            fieldErrors.add(new FieldErrorDTO("roleDetails","roleDetails can not be blank"));
        }
        if(documentLinkDTO.getRoleDetails()!=null){
            String group[]= documentLinkDTO.getRoleDetails().split(",");
            List<String> roleEnums = Stream.of(RoleDetails.values())
                    .map(RoleDetails::name).collect(Collectors.toList());
            for(String groupdetails:group)
                if (!roleEnums.contains(groupdetails)){
                    fieldErrors.add(new FieldErrorDTO("roleDetails","Select proper roleDetails from option"));
                }
        }
        if(documentLinkDTO.getGroupDetails()==null&&documentLinkDTO.getRoleDetails()==null&&
                !documentLinkDTO.getUserId().name().equals(UserId.Bhavin.name())&&
                !documentLinkDTO.getUserId().name().equals(UserId.Harshal.name())&&
                !documentLinkDTO.getUserId().name().equals(UserId.Savan.name())&&
                !documentLinkDTO.getUserId().name().equals(UserId.Vimal.name())){
            fieldErrors.add(new FieldErrorDTO("userId","userId can not be blank Or Select proper from option"));
        }
        return  fieldErrors;
    }

}
