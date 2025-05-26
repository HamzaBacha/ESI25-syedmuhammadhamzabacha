package com.esi.teacherservice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esi.teacherservice.dto.AppointmentDto;
import com.esi.teacherservice.model.Appointment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    @Autowired
    private KafkaTemplate<String, AppointmentDto> kafkaTemplate;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @KafkaListener(topics = "appointment-topic", groupId = "appointment-group")
    public void updateTeacherInfo(AppointmentDto appointmentDto) {
        Appointment appointment = Appointment.builder()
                .appointmentid(appointmentDto.getAppointmentid())
                .studentId(appointmentDto.getStudentId())
                .studentName(appointmentDto.getStudentName())
                .teacherId(appointmentDto.getTeacherId())
                .date(appointmentDto.getDate())
                .build();
                appointmentRepository.save(appointment);
    }

}
