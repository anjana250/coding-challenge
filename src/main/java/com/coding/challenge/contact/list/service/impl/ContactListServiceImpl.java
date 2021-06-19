package com.coding.challenge.contact.list.service.impl;

import com.coding.challenge.contact.list.api.request.ContactListRequest;
import com.coding.challenge.contact.list.api.request.RequestPhone;
import com.coding.challenge.contact.list.api.response.*;
import com.coding.challenge.contact.list.repository.ContactsRepository;
import com.coding.challenge.contact.list.repository.entity.Contacts;
import com.coding.challenge.contact.list.service.ContactListService;
import com.coding.challenge.contact.list.utils.NumberUtils;
import com.coding.challenge.contact.list.utils.ResponseUtils;
import com.coding.challenge.contact.list.utils.StringUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactListServiceImpl implements ContactListService {
    HttpHeaders headers= new HttpHeaders();

    @Autowired
    private ContactsRepository contactsRepository;


    @Override
    public ResponseEntity<ContactListResponseV2> processContactListInsert(String contactListRequestJson)  {
        ContactListResponseV2 contactListResponse= new ContactListResponseV2();
        if(isJsonValid(contactListRequestJson)) {
            ContactListRequest contactListRequest = createRequest(contactListRequestJson);
            saveUpdateContact(null,contactListRequest);
            return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Your request has been processed successfully"));
        }
        return ResponseEntity.badRequest().headers(headers).body(ResponseUtils.returnMessage("There was an issue processing your request. Please try again."));

    }

    private void saveUpdateContact(Contacts contacts,ContactListRequest request){
        if(contacts==null) {
            contacts = new Contacts();
        }
        if(request.getName()!=null) {
            if(request.getName().getFirst()!=null) {
                contacts.setFirstName(request.getName().getFirst());
            }
            if(request.getName().getLast()!=null) {
                contacts.setLastName(request.getName().getLast());
            }
            if(request.getName().getMiddle()!=null) {
                contacts.setMiddleName(request.getName().getMiddle());
            }
        }
        if(request.getAddress()!=null) {
            if(request.getAddress().getStreet()!=null) {
                contacts.setStreetName(request.getAddress().getStreet());
            }
            if(request.getAddress().getCity()!=null) {
                contacts.setCityName(request.getAddress().getCity());
            }
            if(request.getAddress().getState()!=null) {
                contacts.setStateName(request.getAddress().getState());
            }
            if(request.getAddress().getZip()!=null) {
                contacts.setZipCode(request.getAddress().getZip());
            }
        }
        if(request.getPhone()!=null && !request.getPhone().isEmpty()){
            List<RequestPhone> phoneListFromRequest= request.getPhone();
            for(RequestPhone requestPhone: phoneListFromRequest){
                if(requestPhone.getType().equals(RequestPhone.Category.HOME)
                        && StringUtils.isNotNullOrEmpty(requestPhone.getNumber())){
                    contacts.setHomePhone(requestPhone.getNumber());
                }
                if(requestPhone.getType().equals(RequestPhone.Category.MOBILE)
                        && StringUtils.isNotNullOrEmpty(requestPhone.getNumber())){
                    contacts.setMobilePhone(requestPhone.getNumber());
                }
                if(requestPhone.getType().equals(RequestPhone.Category.WORK)
                        && StringUtils.isNotNullOrEmpty(requestPhone.getNumber())){
                    contacts.setWorkPhone(requestPhone.getNumber());
                }

            }

        }
        if(request.getEmail()!=null) {
            contacts.setEmailAddress(request.getEmail());
        }

        contactsRepository.save(contacts);
    }

    @Override
    public ResponseEntity<ContactListResponseV2> processContactListDelete(String contactListRequestJson, String id){
        ContactListResponseV2 contactListResponse = new ContactListResponseV2();
        if (id != null) {
            if (NumberUtils.isInteger(id) && contactsRepository.existsById(Integer.parseInt(id))) {
                contactsRepository.deleteById(Integer.parseInt(id));
                return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Delete was successful"));
            }
            else{
                return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Provide an Integer Value that already exists in database"));
            }
        }
        else{
            return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("You must provide Id to perform a delete"));
        }
    }

    @Override
    public ResponseEntity<ContactListResponseV2> processContactListUpdate(String contactListRequestJson, String id) {
        ContactListResponseV2 contactListResponse = new ContactListResponseV2();
        if(isJsonValid(contactListRequestJson)) {
            if (id != null) {
                if (NumberUtils.isInteger(id)) {
                    Integer idInt= Integer.parseInt(id);
                    if(contactsRepository.existsById(idInt)) {
                        ContactListRequest contactListRequest = createRequest(contactListRequestJson);
                        Optional<Contacts> previouslySavedContact = contactsRepository.findById(idInt);
                        if (previouslySavedContact.isPresent()) {
                            Contacts contact = previouslySavedContact.get();
                            saveUpdateContact(contact, contactListRequest);
                            return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Update was successful."));
                        }

                    }
                    else{
                        return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Provide an Integer Value that is already in the database"));
                    }

                } else {
                    return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Provide an Integer Value"));
                }
                return ResponseEntity.ok().headers(headers).body(contactListResponse);
            }
        }
        return ResponseEntity.badRequest().headers(headers).body(ResponseUtils.returnMessage("There was an issue processing your request. Please try again."));
    }

    @Override
    public ResponseEntity<ContactListResponseV2> processContactListRetrieval(String contactListRequestJson, String id){
        ContactListResponseV2 contactListResponse= new ContactListResponseV2();
        List<Contacts> contactsArrayList= new ArrayList<>();
        if(StringUtils.isNotNullOrEmpty(id)) {
            if(NumberUtils.isInteger(id)) {
                Optional<Contacts> previouslySavedContact = contactsRepository.findById(Integer.parseInt(id));
                if (previouslySavedContact.isPresent()) {
                    contactsArrayList.add(previouslySavedContact.get());
                }
            }else{
                return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Provide an Integer Value"));
            }
        }else{
            contactsArrayList= contactsRepository.findAll();
        }
        if(contactsArrayList!=null && !contactsArrayList.isEmpty()) {
            contactListResponse= ResponseUtils.populateContactListResponse(contactsArrayList, contactListResponse);
            return ResponseEntity.ok().headers(headers).body(contactListResponse);
        }
        return ResponseEntity.ok().headers(headers).body(new ContactListResponseV2());
    }




    public ContactListRequest createRequest(String requestJson)  {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        ContactListRequest contactListRequest= null;
        if(isJsonValid(requestJson)) {
            try {
                contactListRequest = objectMapper.readValue(requestJson, new TypeReference<ContactListRequest>() {
                 });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        return contactListRequest;
    }

    private boolean isJsonValid (String requestJson){
        boolean isJsonValid= false;
        try{
            ObjectMapper mapper= new ObjectMapper();
            JsonNode node= mapper.readTree(requestJson);
            isJsonValid=true;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return isJsonValid;
    }

}
