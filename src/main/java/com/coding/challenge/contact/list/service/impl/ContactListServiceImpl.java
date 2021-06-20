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

import javax.annotation.Nullable;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactListServiceImpl implements ContactListService {
    HttpHeaders headers= new HttpHeaders();

    @Autowired
    private ContactsRepository contactsRepository;

    /**
     * Create Request Object and Insert new record in the Contacts Table
     *
     * @param  contactListRequestJson   Request JSON with Data to be inserted into Contacts Table
     * @return  contactListResponse     Return successful or error response
     */
    @Override
    public ResponseEntity<ContactListResponseV2> processContactListInsert(String contactListRequestJson)  {
        if(isJsonValid(contactListRequestJson)) {
            ContactListRequest contactListRequest = createRequest(contactListRequestJson);
            if(passInitialValidations(contactListRequest)) {
                saveUpdateContact(null, contactListRequest);
                return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Your request has been processed successfully"));
            } else{
                return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Your request is not valid"));
            }
        }
        return ResponseEntity.badRequest().headers(headers).body(ResponseUtils.returnMessage("There was an issue processing your request. Please try again."));

    }

    //Validates that Request Object Contains First Name and Last Name- to prevent those records from being inserted. Also makes sure request object is not null
    private boolean passInitialValidations(ContactListRequest contactListRequest) {
        if(contactListRequest!=null && contactListRequest.getName()!=null) {
            return (StringUtils.isNotNullOrEmpty(contactListRequest.getName().getFirst()) && StringUtils.isNotNullOrEmpty(contactListRequest.getName().getLast()));
        }
        return false;
    }

    /**
     * Insert or Update record in the Contacts Table
     *
     * @param  contacts   Existing Contacts object to be updated
     * @param  request    Request object with data to insert or update Contacts Table
     */

    private void saveUpdateContact(@Nullable Contacts contacts, @NotNull ContactListRequest request){
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

    /**
     * Deletes record in the Contacts Table
     *
     *@param    id                     Id of the Record  to be deleted from Contacts Table
     *@return  contactListResponse     Return successful or error response
     */

    @Override
    public ResponseEntity<ContactListResponseV2> processContactListDelete(String id){
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

    /**
     * Create Request Object and Update existing record in the Contacts Table
     *
     * @param  contactListRequestJson   Request JSON with Data to be inserted into Contacts Table
     * @return  contactListResponse     Return successful or error response
     */

    @Override
    public ResponseEntity<ContactListResponseV2> processContactListUpdate(@NotNull String contactListRequestJson, @NotNull String id) {
        ContactListResponseV2 contactListResponse = new ContactListResponseV2();
        if(isJsonValid(contactListRequestJson)) {
            if (id != null) {
                if (NumberUtils.isInteger(id)) {
                    Integer idInt= Integer.parseInt(id);
                    if(contactsRepository.existsById(idInt)) {
                        ContactListRequest contactListRequest = createRequest(contactListRequestJson);
                        if(passInitialValidations(contactListRequest)) {
                            Optional<Contacts> previouslySavedContact = contactsRepository.findById(idInt);
                            if (previouslySavedContact.isPresent()) {
                                Contacts contact = previouslySavedContact.get();
                                saveUpdateContact(contact, contactListRequest);
                                return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Update was successful."));
                            }
                        }else {
                            return ResponseEntity.ok().headers(headers).body(ResponseUtils.returnMessage("Your request is not valid"));
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

    /**
     * Deletes record in the Contacts Table
     *
     *@param    id                     Id of the Record  to be deleted from Contacts Table- if null return all
     *@return  contactListResponse     Return successful or error response
     */

    @Override
    public ResponseEntity<ContactListResponseV2> processContactListRetrieval(@Nullable String id){
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


    //Creates Request Object
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

    //Validates that the JSON received is valid
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
