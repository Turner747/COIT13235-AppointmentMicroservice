package com.optimed.appointmentmicroservice.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimed.appointmentmicroservice.model.Appointment;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AppointmentResponse {
    private long id;
    @JsonProperty("patient")
    private PatientResponse patient;
    @JsonProperty("doctor")
    private StaffResponse doctor;
    @JsonProperty("shift")
    private ShiftResponse shift;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date finishTime;
    @Enumerated(EnumType.STRING)
    private Appointment.AppointmentStatus status;
}
