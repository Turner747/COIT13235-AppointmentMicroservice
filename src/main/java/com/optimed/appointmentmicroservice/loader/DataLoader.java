package com.optimed.appointmentmicroservice.loader;

import com.optimed.appointmentmicroservice.feignclient.PatientClient;
import com.optimed.appointmentmicroservice.feignclient.StaffClient;
import com.optimed.appointmentmicroservice.mapper.ObjectMapper;
import com.optimed.appointmentmicroservice.model.Appointment;
import com.optimed.appointmentmicroservice.repository.AppointmentRepository;
import com.optimed.appointmentmicroservice.response.PatientResponse;
import com.optimed.appointmentmicroservice.response.ShiftResponse;
import com.optimed.appointmentmicroservice.response.StaffResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {
    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private StaffClient staffClient;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        PatientResponse patient = ObjectMapper.map(patientClient.getPatientById(1L).getBody(), PatientResponse.class);
        StaffResponse doctor = ObjectMapper.map(staffClient.getStaffByID(3L).getBody(), StaffResponse.class);
        ShiftResponse shift = ObjectMapper.map(staffClient.getShiftById(1L).getBody(), ShiftResponse.class);

        createAppointment(patient, doctor, shift, "2023-10-09 09:00", "2023-10-09 09:30");
    }

    public void createAppointment(PatientResponse patient,
                               StaffResponse doctor,
                               ShiftResponse shift,
                               String startTime,
                               String finishTime) {
        String datePattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setShift(shift);
        try {
            appointment.setStartTime(dateFormat.parse(startTime));
            appointment.setFinishTime(dateFormat.parse(finishTime));
        } catch (ParseException e) {
            System.out.println("Date format: yyyy-MM-dd HH:mm");
        }
        appointment.setStatus(Appointment.AppointmentStatus.ACTIVE);

        appointmentRepo.save(appointment);
    }

}
