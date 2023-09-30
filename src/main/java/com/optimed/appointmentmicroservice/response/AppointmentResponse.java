package com.optimed.appointmentmicroservice.response;

import com.optimed.appointmentmicroservice.model.Appointment;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentResponse {
    private long id;
    private PatientResponse patient;
    private StaffResponse doctor;
    private ShiftResponse shift;
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;
    @Enumerated(EnumType.STRING)
    private Appointment.AppointmentStatus status;
}
