package com.coding.challenge.contact.list.servicetests;


import com.coding.challenge.contact.list.api.request.ContactListRequest;
import com.coding.challenge.contact.list.api.response.ContactListResponseV2;
import com.coding.challenge.contact.list.repository.ContactsRepository;
import com.coding.challenge.contact.list.service.impl.ContactListServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class ContactListServiceImplTests  {

    @Mock
    private ContactsRepository contactsRepository;

    @InjectMocks
    ContactListServiceImpl contactListService;


    static ObjectMapper mapper= new ObjectMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void validInsert(){
        String contactListRequestJson = "{\n" +
                "  \"name\": {\n" +
                "    \"first\": \"Harold\",\n" +
                "    \"middle\": \"Francis\",\n" +
                "    \"last\": \"Gilkey\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"street\": \"8360 High Autumn Row\",\n" +
                "    \"city\": \"Cannon\",\n" +
                "    \"state\": \"Delaware\",\n" +
                "    \"zip\": \"19797\"\n" +
                "  },\n" +
                "  \"phone\": [\n" +
                "    {\n" +
                "      \"number\": \"302-611-9148\",\n" +
                "      \"type\": \"home\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"number\": \"302-532-9427\",\n" +
                "      \"type\": \"mobile\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"email\": \"harold.gilkey@yahoo.com\"\n" +
                "}\n";

        ResponseEntity<ContactListResponseV2> contactListResponse= contactListService.processContactListInsert(contactListRequestJson);
        assertTrue(contactListResponse.getBody().getAdditionalProperties().containsValue("Your request has been processed successfully"));
    }

    @Test
    public void invalidInsertFirstNameEmpty(){
        String contactListRequestJson = "{\n" +
                "  \"name\": {\n" +
                "    \"first\": null,\n" +
                "    \"middle\": \"Francis\",\n" +
                "    \"last\": \"Gilkey\"\n" +
                "  },\n" +
                "  \"address\": {\n" +
                "    \"street\": \"8360 High Autumn Row\",\n" +
                "    \"city\": \"Cannon\",\n" +
                "    \"state\": \"Delaware\",\n" +
                "    \"zip\": \"19797\"\n" +
                "  },\n" +
                "  \"phone\": [\n" +
                "    {\n" +
                "      \"number\": \"302-611-9148\",\n" +
                "      \"type\": \"home\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"number\": \"302-532-9427\",\n" +
                "      \"type\": \"mobile\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"email\": \"harold.gilkey@yahoo.com\"\n" +
                "}\n";

        ResponseEntity<ContactListResponseV2> contactListResponse= contactListService.processContactListInsert(contactListRequestJson);
        assertTrue(contactListResponse.getBody().getAdditionalProperties().containsValue("Your request is not valid"));
    }



}
