package com.tatvacoconet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.tatvacoconet.entity.VerticalData;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocumentDataControllerTest {
    public static DocumentLinkDTO documentlinkDTO = new DocumentLinkDTO("Vimal",null,null);
    public static DocumentDTO documentDTO = new DocumentDTO("pdf file",
                                                            "ProjectA",
                                                             getDate("2017-06-06"),
                                                             getDate("2017-06-06"),
                                                             getDate("2017-06-06"),
                                                             getDate("2017-06-11"),
                                                             VerticalData.BOI,
                                                            "ForYourInformation",
                                                            "Account_Statement",
                                                             AddressScope.UserId,
                                                             documentlinkDTO);
    @Autowired
    private TestRestTemplate restTemplate;

    private static Date getDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test1FileUpload_Save() {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", new ClassPathResource("110.pdf"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/fileUpload", requestEntity, String.class);
        String resp = responseEntity.getBody();
        JSONObject json = new JSONObject(resp);
            assertNotNull(json.getLong("documentId"));
        documentDTO.setDocumentId(json.getLong("documentId"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void test2Document_Save() {
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("/DocumentAdd", documentDTO, String.class);
        String resp = responseEntity.getBody();

        JSONObject json = new JSONObject(resp);
            assertNotNull(json.getLong("documentId"));
        documentDTO.setDocumentId(json.getLong("documentId"));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void test3Document_List_By_Id() {
        ResponseEntity<DocumentDTO> responseEntity = this.restTemplate
                .getForEntity("/documentListByID/" + documentDTO.getDocumentId(),DocumentDTO.class);
        DocumentDTO documenttestDTO = responseEntity.getBody();
        assertThat(documenttestDTO.getDocumentTag().equals(
                documentDTO.getDocumentTag()));
        documentDTO.setDocumentId(documenttestDTO.getDocumentId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test4Document_Update() {
        documentDTO.setDocumentTag("ProjectA,ProjectB");
        ResponseEntity<DocumentDTO> responseEntity = this.restTemplate
                .postForEntity("/updateDocument", documentDTO,DocumentDTO.class);
        DocumentDTO status = responseEntity.getBody();
        assertThat(status.getDocumentTag().equals(documentDTO.getDocumentTag()));
        assertThat(status.getDocumentType().equals(documentDTO.getDocumentType()));
        documentDTO.setDocumentId(status.getDocumentId());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void test5Document_Delete() {
        ResponseEntity<DocumentDTO> responseEntity = this.restTemplate
                .getForEntity("/deleteDocument/" + documentDTO.getDocumentId(),DocumentDTO.class);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
