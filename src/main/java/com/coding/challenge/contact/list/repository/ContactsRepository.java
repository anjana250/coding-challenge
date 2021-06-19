package com.coding.challenge.contact.list.repository;


import com.coding.challenge.contact.list.repository.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository  extends JpaRepository<Contacts, Integer> {

    @Query(value= "SELECT * FROM CONTACTS c WHERE c.home_phone IS NOT NULL", nativeQuery = true)
    List<Contacts>  findAllWithHomePhone();
}
