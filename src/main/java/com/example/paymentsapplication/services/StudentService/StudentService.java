package com.example.paymentsapplication.services.StudentService;

import com.example.paymentsapplication.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO>  findAllStudents();

    StudentDTO findStudentByCode(String code);

    List<StudentDTO> findStudentByProgramId(String programId);
}
