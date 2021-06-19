package com.coding.challenge.contact.list.service.impl;

import com.coding.challenge.contact.list.api.response.ContactListResponseV2;
import com.coding.challenge.contact.list.api.response.Phone;
import com.coding.challenge.contact.list.api.response.list.Call;
import com.coding.challenge.contact.list.api.response.list.CallListResponse;
import com.coding.challenge.contact.list.api.response.list.Name;
import com.coding.challenge.contact.list.repository.ContactsRepository;
import com.coding.challenge.contact.list.repository.entity.Contacts;
import com.coding.challenge.contact.list.service.CallListService;
import com.coding.challenge.contact.list.utils.DataComparator;
import com.coding.challenge.contact.list.utils.ResponseUtils;
import com.coding.challenge.contact.list.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CallListServiceImpl implements CallListService {
    HttpHeaders headers= new HttpHeaders();

    @Autowired
    private ContactsRepository contactsRepository;

    /**
     * Generates Call List of all Records in Contacts Table with Home Phone
     *
     *
     *@return  contactListResponse     Return successful or error response
     */

    @Override
    public ResponseEntity<CallListResponse> processCallListRetrieval(){
        CallListResponse callListResponse= new CallListResponse();
        List<Contacts> contactsArrayList= contactsRepository.findAllWithHomePhone();
        if(contactsArrayList!=null && !contactsArrayList.isEmpty()){
            //Order Call List  Alphabetically Last Name then First Name
            if(contactsArrayList.size()>1){
                Collections.sort(contactsArrayList, DataComparator.Contacts_Alphabetical_Comparator);
            }
            callListResponse= populateCallListResponse(callListResponse,contactsArrayList);
        }
        return ResponseEntity.ok().headers(headers).body(callListResponse);
    }


    //Creates the Call List Response
    private CallListResponse populateCallListResponse(CallListResponse callListResponse,List<Contacts> contactsArrayList){
        List<Call> callList = new ArrayList<>();
        for(Contacts contact: contactsArrayList){
            if(contact!=null){
                Call call = new Call();
                Name name = new Name();
                if(StringUtils.isNotNullOrEmpty(contact.getFirstName())){
                    name.setFirst(contact.getFirstName());
                }
                if(StringUtils.isNotNullOrEmpty(contact.getLastName())){
                    name.setLast(contact.getLastName());
                }
                if(StringUtils.isNotNullOrEmpty(contact.getMiddleName())){
                    name.setMiddle(contact.getMiddleName());
                }
                call.setName(name);
                if(StringUtils.isNotNullOrEmpty(contact.getHomePhone())){
                    call.setPhone(contact.getHomePhone());
                }
                callList.add(call);

            }
        }
        callListResponse.setCallList(callList);

        return callListResponse;
    }
}
