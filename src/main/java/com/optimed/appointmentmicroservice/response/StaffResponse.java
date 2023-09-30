package com.optimed.appointmentmicroservice.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
@Embeddable
public class StaffResponse {
    @Column(name = "staff_id")
    private long id;
    @Column(name = "staff_first_name")
    private String first_name;
    @Column(name = "staff_surname")
    private String surname;
//    @Temporal(TemporalType.DATE)
//    private Date dob;
//    private String street;
//    private String suburb;
//    private String state;
//    private int postcode;
    @Column(name = "staff_phone")
    private String phone;
    @Column(name = "staff_email")
    private String email;
//    private String password;
    @Column(name = "staff_provider_number")
    private String provider_number;
    @Column(name = "staff_prescriber_number")
    private String prescriber_number;
}