package com.coding.challenge.contact.list.servicetests;

import com.coding.challenge.contact.list.ListApplication;
import com.coding.challenge.contact.list.api.response.list.Call;
import com.coding.challenge.contact.list.api.response.list.CallListResponse;
import com.coding.challenge.contact.list.repository.ContactsRepository;
import com.coding.challenge.contact.list.repository.entity.Contacts;
import com.coding.challenge.contact.list.service.CallListService;
import com.coding.challenge.contact.list.service.impl.CallListServiceImpl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
public class CallListServiceImplTests {
    @Mock
    private ContactsRepository contactsRepository;

    @InjectMocks
    private CallListServiceImpl callListService;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validCallListWithPhone(){
        Mockito.when(contactsRepository.findAllWithHomePhone()).thenReturn(createContactList("AA","BB","CC","DD"));
        ResponseEntity<CallListResponse> callListResponse= callListService.processCallListRetrieval();
        assertFalse(callListResponse.getBody().getCallList().isEmpty());
    }

    @Test
    public void sortAlphabetical(){
        Mockito.when(contactsRepository.findAllWithHomePhone()).thenReturn(createContactList("CC","BB","AA","BB"));
        ResponseEntity<CallListResponse> callListResponse= callListService.processCallListRetrieval();
        assertTrue(validateCallListResponse(callListResponse.getBody(), "AA"));
    }

    private boolean validateCallListResponse(CallListResponse callListResponse, String firstName){
        if(callListResponse!=null && callListResponse.getCallList()!=null && !callListResponse.getCallList().isEmpty()){
            List<Call> callList= callListResponse.getCallList();
            if(callList.stream().findFirst().isPresent()){
                Call call= callList.stream().findFirst().get();
                if(call.getName().getFirst().equalsIgnoreCase(firstName)){
                    return true;
                }
            }
        }
        return false;
    }

    private List<Contacts> createContactList(String firstNameOne, String lastNameOne, String firstNameTwo, String lastNameTwo){
        List<Contacts> contactsList= new ArrayList<>();
        Contacts contact1= createContact(firstNameOne, lastNameOne);
        contactsList.add(contact1);
        Contacts contact2= createContact(firstNameTwo, lastNameTwo);
        contactsList.add(contact2);
        return contactsList;
    }

    private Contacts createContact(String firstName, String lastName){
        Contacts contacts= new Contacts();
        contacts.setId(1);
        contacts.setFirstName(firstName);
        contacts.setMiddleName("Jane");
        contacts.setLastName(lastName);
        contacts.setCityName("Richmond");
        contacts.setStateName("Virginia");
        contacts.setZipCode("12345");
        contacts.setMobilePhone("123456789");
        contacts.setWorkPhone("123456789");
        contacts.setEmailAddress("abc@abc.com");
        contacts.setHomePhone("123456789");

        return contacts;
    }
}
