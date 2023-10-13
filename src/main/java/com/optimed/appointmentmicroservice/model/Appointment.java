package com.optimed.appointmentmicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import org.springframework.format.annotation.DateTimeFormat;

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
//    @JsonManagedReference
    private PatientResponse patient;
    @Embedded
//    @JsonManagedReference
    private StaffResponse doctor;
    @Embedded
//    @JsonManagedReference
    private ShiftResponse shift;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "start_time")
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "finish_time")
    private Date finishTime;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date", updatable=false)
    @CreationTimestamp
    @JsonIgnore
    private Date insertDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    @UpdateTimestamp
    @JsonIgnore
    private Date updatedDate;

    @PreUpdate
    @PrePersist
    public void validateAppointment() throws IllegalArgumentException {
        if(this.startTime.before(this.shift.getStartTime())
                || this.finishTime.after(this.shift.getFinishTime())
                || this.startTime.after(this.finishTime))
            throw new IllegalArgumentException("Appointment scheduling error");
    }
}
