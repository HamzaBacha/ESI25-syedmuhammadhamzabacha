package com.esi.studentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.studentservice.dto.StudentDto;
import com.esi.studentservice.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.esi.studentservice.repository.StudentRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebClient webClient;

    public void addStudentInfo(StudentDto studentDto) {
        Student student = Student.builder()
                .studentId(studentDto.getStudentId())
                .studentName(studentDto.getStudentName())
                .studentData(studentDto.getStudentData())
                .build();

                studentRepository.save(student);
    }

    private StudentDto mapToStudentDto(Student student) {
        return StudentDto.builder()
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .studentData(student.getStudentData())
                .build();
    }

    public String fetchStudentData(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getStudentData();
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToStudentDto).toList();
    }
}
