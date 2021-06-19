package com.coding.challenge.contact.list.utils;

import com.coding.challenge.contact.list.api.response.*;
import com.coding.challenge.contact.list.repository.entity.Contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseUtils {

    //Exception Message if Integer is not Provided
    public static ContactListResponseV2 returnMessage(String value){
        ContactListResponseV2 contactListResponseV2= new ContactListResponseV2();
        contactListResponseV2.setAdditionalProperty("Message Returned",value);
        return contactListResponseV2;
    }

    public static ContactListResponseV2 populateContactListResponse(List<Contacts> contactsArrayList, ContactListResponseV2 contactListResponse){
        List<Contact> contactResponseList= new ArrayList<>();

        for(Contacts contactFromDB: contactsArrayList) {
            if(contactFromDB!=null) {
                Contact contactForReponse= new Contact();
                contactForReponse.setId(contactFromDB.getId().toString());
                Name responseName = new Name();
                if(contactFromDB.getFirstName()!=null) {
                    responseName.setFirst(contactFromDB.getFirstName());
                }
                if(contactFromDB.getMiddleName()!=null){
                    responseName.setMiddle(contactFromDB.getMiddleName());
                }
                if(contactFromDB.getLastName()!=null){
                    responseName.setLast(contactFromDB.getLastName());
                }
                contactForReponse.setName(responseName);
                Address address= new Address();
                if(contactFromDB.getStreetName()!=null){
                    address.setStreet(contactFromDB.getStreetName());
                }
                if(contactFromDB.getCityName()!=null){
                    address.setCity(contactFromDB.getCityName());
                }
                if(contactFromDB.getStateName()!=null){
                    address.setState(contactFromDB.getStateName());
                }
                if(contactFromDB.getZipCode()!=null){
                    address.setZip(contactFromDB.getZipCode());
                }
                contactForReponse.setAddress(address);
                List<Phone> phoneNumberList= new ArrayList<>();
                if(contactFromDB.getHomePhone()!=null){
                    Phone phone= new Phone();
                    phone.setType(Phone.Category.HOME);
                    phone.setNumber(contactFromDB.getHomePhone());
                    phoneNumberList.add(phone);
                }
                if(contactFromDB.getMobilePhone()!=null){
                    Phone phone= new Phone();
                    phone.setType(Phone.Category.MOBILE);
                    phone.setNumber(contactFromDB.getMobilePhone());
                    phoneNumberList.add(phone);
                }
                if(contactFromDB.getWorkPhone()!=null){
                    Phone phone= new Phone();
                    phone.setType(Phone.Category.WORK);
                    phone.setNumber(contactFromDB.getWorkPhone());
                    phoneNumberList.add(phone);
                }
                contactForReponse.setPhone(phoneNumberList);
                contactResponseList.add(contactForReponse);
            }
        }
        contactListResponse.setContactList(contactResponseList);
        return contactListResponse;
    }
}
