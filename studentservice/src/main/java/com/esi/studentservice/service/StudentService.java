package com.esi.studentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
