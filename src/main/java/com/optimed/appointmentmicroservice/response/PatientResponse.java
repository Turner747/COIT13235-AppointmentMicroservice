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
    private String firstName;
    @Column(name = "patient_last_name")
    private String surname;
}
