package com.coding.challenge.contact.list.service;

import com.coding.challenge.contact.list.api.response.list.CallListResponse;
import org.springframework.http.ResponseEntity;

public interface CallListService {

    public ResponseEntity<CallListResponse> processCallListRetrieval(String contactListRequestJson);
}
