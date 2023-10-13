package com.optimed.appointmentmicroservice.feignclient;

import com.optimed.appointmentmicroservice.response.PatientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("patient-service")
public interface PatientClient {
    @GetMapping("/patients/id/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable("id") Long id);
}
