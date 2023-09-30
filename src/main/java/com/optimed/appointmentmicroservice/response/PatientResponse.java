package com.optimed.appointmentmicroservice.response;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PatientResponse {
    @Column(name = "patient_id")
    private long id;
    @Column(name = "patient_first_name")
    private String first_name;
    @Column(name = "patient_surname")
    private String surname;
}
