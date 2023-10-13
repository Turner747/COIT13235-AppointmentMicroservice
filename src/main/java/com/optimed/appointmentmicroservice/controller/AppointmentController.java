package com.optimed.appointmentmicroservice.controller;

import com.optimed.appointmentmicroservice.feignclient.StaffClient;
import com.optimed.appointmentmicroservice.mapper.ObjectMapper;
import com.optimed.appointmentmicroservice.model.Appointment;
import com.optimed.appointmentmicroservice.repository.AppointmentRepository;
import com.optimed.appointmentmicroservice.response.AppointmentResponse;
import com.optimed.appointmentmicroservice.response.ShiftResponse;
import com.optimed.appointmentmicroservice.response.StaffResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/restapi/appointments")
public class AppointmentController {
    private AppointmentRepository appointmentRepo;
    private StaffClient staffClient;
    @PostMapping(consumes = "application/json")
    public ResponseEntity<AppointmentResponse> saveAppointment(@RequestBody AppointmentResponse appointmentResponse) {

        ShiftResponse shiftResponse = ObjectMapper.map(staffClient.getShiftById(appointmentResponse.getShift().getId()).getBody(), ShiftResponse.class);
        StaffResponse staffResponse = ObjectMapper.map(staffClient.getStaffByID(appointmentResponse.getDoctor().getId()).getBody(), StaffResponse.class);
        appointmentResponse.setShift(shiftResponse);
        appointmentResponse.setDoctor(staffResponse);

        Appointment newAppointment = appointmentRepo.save(ObjectMapper.map(appointmentResponse, Appointment.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapper.map(newAppointment, AppointmentResponse.class));
    }
    @GetMapping
    public ResponseEntity<Collection<AppointmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepo.findAll();
        if(appointments.isEmpty())
            return ResponseEntity.notFound().build();
        List<AppointmentResponse> appointmentResponses = ObjectMapper.mapAll(appointments, AppointmentResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponses);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable("id") long id) {
        Optional<Appointment> optional = appointmentRepo.findById(id);
        if(optional.isEmpty())
            return ResponseEntity.notFound().build();
        AppointmentResponse appointmentResponse = ObjectMapper.map(optional.get(), AppointmentResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
    }
}
