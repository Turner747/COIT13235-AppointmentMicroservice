package com.optimed.appointmentmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.optimed.appointmentmicroservice.response.PatientResponse;
import com.optimed.appointmentmicroservice.response.ShiftResponse;
import com.optimed.appointmentmicroservice.response.StaffResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")
public class Appointment {
    public enum AppointmentStatus {
        ACTIVE, CANCELLED, RESCHEDULED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private PatientResponse patient;
    @Embedded
    private StaffResponse doctor;
    @Embedded
    private ShiftResponse shift;
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable=false)
    @CreationTimestamp
    @JsonIgnore
    private Date insert_date;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonIgnore
    private Date updated_date;
}
