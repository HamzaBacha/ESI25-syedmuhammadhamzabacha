package com.esi.teacherservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.teacherservice.dto.AppointmentDto;
import com.esi.teacherservice.service.TeacherService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Task 4
    
     @GetMapping("/appointment")
      public List<AppointmentDto> getAllAppointments() {
      return teacherService.getAllAppointments();
     }
     

}
