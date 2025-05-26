package com.esi.appointmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esi.appointmentservice.dto.AppointmentDto;
import com.esi.appointmentservice.model.Appointment;
import com.esi.appointmentservice.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  public void appointmentBooked(AppointmentDto appointmentDto) {
  
    Appointment  appointment = Appointment.builder()
        .appointmentid(appointmentDto.getAppointmentid())
        .studentId(appointmentDto.getStudentId())
        .studentName(appointmentDto.getStudentName())
        .teacherId(appointmentDto.getTeacherId())
        .date(appointmentDto.getDate())
        .build();

      
        appointmentRepository.save(appointment);
  }
  private AppointmentDto mapToAppointmentDto(Appointment appointment) {
    return AppointmentDto.builder()
        .appointmentid(appointment.getAppointmentid())
        .studentId(appointment.getStudentId())
        .studentName(appointment.getStudentName())
        .teacherId(appointment.getTeacherId())
        .date(appointment.getDate())
        .build();
  }

}
