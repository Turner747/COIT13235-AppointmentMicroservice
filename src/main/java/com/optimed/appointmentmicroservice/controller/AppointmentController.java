package com.optimed.appointmentmicroservice.controller;

import com.optimed.appointmentmicroservice.mapper.ObjectMapper;
import com.optimed.appointmentmicroservice.model.Appointment;
import com.optimed.appointmentmicroservice.repository.AppointmentRepository;
import com.optimed.appointmentmicroservice.response.AppointmentResponse;
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
    @PostMapping
    public ResponseEntity<AppointmentResponse> saveAppointment(@RequestBody Appointment appointment) {
        Appointment new_appointment = appointmentRepo.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapper.map(new_appointment, AppointmentResponse.class));
    }
    @GetMapping
    public ResponseEntity<Collection<AppointmentResponse>> getAllAppointments() {
        List<Appointment> appointments = appointmentRepo.findAll();
        if(appointments.isEmpty())
            return ResponseEntity.notFound().build();
        List<AppointmentResponse> appointmentResponses = ObjectMapper.mapAll(appointments, AppointmentResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponses);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable("id") long id, @RequestBody Appointment updatedAppointment) {
        Optional<Appointment> optional = appointmentRepo.findById(id);
        if(optional.isEmpty())
            return ResponseEntity.notFound().build();
        Appointment existingAppointment = optional.get();
        existingAppointment = ObjectMapper.map(updatedAppointment, Appointment.class);

        AppointmentResponse appointmentResponse = ObjectMapper.map(appointmentRepo.save(existingAppointment), AppointmentResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
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
