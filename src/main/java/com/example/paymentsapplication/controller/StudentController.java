package com.example.paymentsapplication.controller;

import com.example.paymentsapplication.dto.StudentDTO;
import com.example.paymentsapplication.services.PaymentService.PaymentService;
import com.example.paymentsapplication.services.StudentService.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class StudentController {
    private StudentService studentService;

    @GetMapping("/students")
    public List<StudentDTO>  getAllStudents(){
        return studentService.findAllStudents();

    }

    @GetMapping("/students/{code}")
    public StudentDTO findStudentByCode(@PathVariable String code){
        return studentService.findStudentByCode(code);
    }


    @GetMapping("/students/programs")
    public List<StudentDTO> findStudentByProgramId(@RequestParam String programId){
        return studentService.findStudentByProgramId(programId);
    }


}
