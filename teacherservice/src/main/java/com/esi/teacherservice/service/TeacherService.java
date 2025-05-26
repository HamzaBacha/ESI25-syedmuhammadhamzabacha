package com.esi.teacherservice.service;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esi.teacherservice.dto.AppointmentDto;
import com.esi.teacherservice.model.Appointment;
import com.esi.teacherservice.repository.TeacherRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    @Autowired
    private KafkaTemplate<String, AppointmentDto> kafkaTemplate;

    @Autowired
    private TeacherRepository teacherRepository;

    @KafkaListener(topics = "appointment-topic", groupId = "appointment-group")
    public void updateTeacherInfo(AppointmentDto appointmentDto) {
        Appointment appointment = Appointment.builder()
                .appointmentid(appointmentDto.getAppointmentid())
                .studentId(appointmentDto.getStudentId())
                .studentName(appointmentDto.getStudentName())
                .teacherId(appointmentDto.getTeacherId())
                .date(appointmentDto.getDate())
                .build();
                teacherRepository.save(appointment);
    }

  
     public   List<AppointmentDto> getAllAppointments(){
        List<Appointment> appointments =  new ArrayList<>();
        teacherRepository.findAll().forEach(appointments::add);
        return appointments.stream().map(this::mapToAppointmentDto).toList();
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
