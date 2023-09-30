package com.optimed.appointmentmicroservice.response;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
@Embeddable
public class ShiftResponse {
    @Column(name = "shift_id")
    private long id;
//    private StaffResponse staff;
    @Column(name = "shift_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;
    @Column(name = "shift_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;
}

