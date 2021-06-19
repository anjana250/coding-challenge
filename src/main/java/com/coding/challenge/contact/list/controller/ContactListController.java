package com.coding.challenge.contact.list.controller;

import com.coding.challenge.contact.list.api.response.ContactListResponseV2;
import com.coding.challenge.contact.list.api.response.ContactListResponseV2;
import com.coding.challenge.contact.list.api.response.list.CallListResponse;
import com.coding.challenge.contact.list.constants.APIConstants;
import com.coding.challenge.contact.list.service.CallListService;
import com.coding.challenge.contact.list.service.ContactListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping(value= APIConstants.CONTACT_LIST_REQUEST_MAPPING, produces = "application/json", consumes= "application/json")
@Log4j2
public class ContactListController {
    @Autowired
    ContactListService contactListService;

    @Autowired
    CallListService callListService;

    @Autowired
    ObjectMapper objectMapper;


    @GetMapping("")
    public ResponseEntity<ContactListResponseV2> getContactList(
            @Valid @RequestBody final String contactListRequestJson){
        return contactListService.processContactListRetrieval( null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactListResponseV2> getContactById(
            @Valid @RequestBody final String contactListRequestJson, @PathVariable ("id") String id) {
        return contactListService.processContactListRetrieval(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ContactListResponseV2> updateContactById(
            @Valid @RequestBody final String contactListRequestJson, @PathVariable ("id") String id) {
        return contactListService.processContactListUpdate(contactListRequestJson, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ContactListResponseV2> deleteContactById(
            @Valid @RequestBody final String contactListRequestJson, @PathVariable ("id") String id) {
        return contactListService.processContactListDelete(id);
    }

    @PostMapping("")
    public ResponseEntity<ContactListResponseV2> responseContactList(
            @Valid @RequestBody final String contactListRequestJson){
        return contactListService.processContactListInsert(contactListRequestJson);
    }

    @GetMapping("/call-list")
    public ResponseEntity<CallListResponse> responseCallList(
            @Valid @RequestBody final String contactListRequestJson){
        return callListService.processCallListRetrieval();
    }


}
