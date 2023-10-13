package com.optimed.appointmentmicroservice.feignclient;

import com.optimed.appointmentmicroservice.response.PatientResponse;
import com.optimed.appointmentmicroservice.response.ShiftResponse;
import com.optimed.appointmentmicroservice.response.StaffResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="staff-service")
public interface StaffClient {
    @GetMapping("restapi/staffs/id/{id}")
    public ResponseEntity<StaffResponse> getStaffByID(@PathVariable("id") long id);
    @GetMapping("restapi/shifts/id/{id}")
    public ResponseEntity<ShiftResponse> getShiftById(@PathVariable("id") long id);
//    @GetMapping("restapi/patients/id/{id}")
//    public ResponseEntity<PatientResponse> getPatientById(@PathVariable("id") long id);
}
