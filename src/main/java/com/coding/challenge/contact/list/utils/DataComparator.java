package com.coding.challenge.contact.list.utils;

import com.coding.challenge.contact.list.repository.entity.Contacts;

import java.util.Comparator;

public class DataComparator {

    public static final Comparator<Contacts> Contacts_Alphabetical_Comparator
            = Comparator.comparing(Contacts::getLastName)
            .thenComparing(Contacts::getFirstName);

}
