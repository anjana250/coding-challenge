package com.coding.challenge.contact.list.service;


import com.coding.challenge.contact.list.api.response.ContactListResponseV2;
import org.springframework.http.ResponseEntity;

public interface ContactListService {
    public ResponseEntity<ContactListResponseV2> processContactListInsert(String contactListRequestJson);
    public ResponseEntity<ContactListResponseV2> processContactListUpdate(String contactListRequestJson, String id);
    public ResponseEntity<ContactListResponseV2> processContactListDelete(String id);
    public ResponseEntity<ContactListResponseV2> processContactListRetrieval(String id);

}
