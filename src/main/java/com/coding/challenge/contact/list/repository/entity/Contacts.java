package com.coding.challenge.contact.list.repository.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name= "Contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "middle_name")
    private String middleName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name= "street_name")
    private String streetName;

    @Column(name= "city_name")
    private String cityName;

    @Column(name= "state_name")
    private String stateName;

    @Column(name= "zip_code")
    private String zipCode;

    @Column(name= "home_phone")
    private String homePhone;

    @Column(name= "work_phone")
    private String workPhone;

    @Column(name= "mobile_phone")
    private String mobilePhone;

    @Column(name= "email_address")
    private String emailAddress;


}
