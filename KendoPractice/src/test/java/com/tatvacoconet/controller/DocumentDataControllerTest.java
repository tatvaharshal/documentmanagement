/*package com.tatvacoconet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.DateUtil;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import com.tatvacoconet.dto.DocumentDTO;
import com.tatvacoconet.dto.DocumentLinkDTO;
import com.tatvacoconet.entity.AddressScope;
import com.tatvacoconet.entity.DocumentStatus;
import com.tatvacoconet.entity.DocumentType;
import com.tatvacoconet.entity.UserId;
import com.tatvacoconet.entity.VerticalData;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocumentDataControllerTest {
    public static DocumentLinkDTO documentlinkDTO = new DocumentLinkDTO(UserId.Vimal,null,null);
    public static DocumentDTO documentDTO = new DocumentDTO( "pdf file","ProjectA",
            DateUtil.parseDatetime("2017-05-29T00:00:00.000Z"),
            DateUtil.parseDatetime("2017-05-29T00:00:00.000Z"),
            DateUtil.parseDatetime("2017-05-31T00:00:00.000Z"),
            DateUtil.parseDatetime("2017-06-11T00:00:00.000Z"),
            VerticalData.BOI,DocumentStatus.ForYourInformation,
            DocumentType.Account_Statement,AddressScope.UserId,
            documentlinkDTO);
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1FileUpload_Save(){
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new ClassPathResource("110.pdf"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<LinkedMultiValueMap<String, Object>>(map, headers);

        ResponseEntity<String> responseEntity =
                this.restTemplate.postForEntity("/fileUpload", requestEntity, String.class);
        String resp = responseEntity.getBody();
        JSONObject json = new JSONObject(resp);
        if(json != null)
            assertNotNull(json.getLong("documentId"));
        // assertThat(json.getLong("documentId")).isEqualTo(documentDTO.getDocumentId());
        documentDTO.setDocumentId(json.getLong("documentId"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    public void test2Document_Save(){
        ResponseEntity<String> responseEntity =
                this.restTemplate.postForEntity("/DocumentAdd", documentDTO, String.class);
        String resp = responseEntity.getBody();

        JSONObject json = new JSONObject(resp);
        if(json != null)
            assertNotNull(json.getLong("documentId"));
        // assertThat(json.getLong("documentId")).isEqualTo(documentDTO.getDocumentId());
        documentDTO.setDocumentId(json.getLong("documentId"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    public void test3Document_List_By_Id(){
        ResponseEntity<DocumentDTO> responseEntity =
                this.restTemplate.getForEntity("/documentListByID/"+documentDTO.getDocumentId(),
                        DocumentDTO.class);

        DocumentDTO documenttestDTO = responseEntity.getBody();
        assertThat(documenttestDTO.getDocumentTag().equals(documentDTO.getDocumentTag()));
        documentDTO.setDocumentId(documenttestDTO.getDocumentId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void test4Document_Update(){
        documentDTO.setDocumentTag("ProjectA,ProjectB");
        ResponseEntity<DocumentDTO> responseEntity = this.restTemplate.postForEntity("/updateDocument",
                documentDTO, DocumentDTO.class);
        DocumentDTO status = responseEntity.getBody();
        assertThat(status.getDocumentTag().equals(documentDTO.getDocumentTag()));
        assertThat(status.getDocumentType().equals(documentDTO.getDocumentType()));
        documentDTO.setDocumentId(status.getDocumentId());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    public void test5Document_Delete(){
        ResponseEntity<DocumentDTO> responseEntity = this.restTemplate.getForEntity
                ("/deleteDocument/"+documentDTO.getDocumentId(),DocumentDTO.class);
        //DocumentDTO status = responseEntity.getBody();
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
*/