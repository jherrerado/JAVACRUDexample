package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Struct;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService { // BUSSINESS PLAYER
    private final StudentRespository studentRespository;

    @Autowired
    public StudentService(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }


    public List<Student> getStudent() {
        return studentRespository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmailOptional = studentRespository.findStudentByEmail(student.getEmail());
        if(studentByEmailOptional.isPresent()){
            try {
                throw new IllegalAccessException("email taken");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        studentRespository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRespository.existsById(studentId);
        if(!exist){
            try {
                throw new IllegalAccessException("studnet with id " + studentId + "does not exists");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
