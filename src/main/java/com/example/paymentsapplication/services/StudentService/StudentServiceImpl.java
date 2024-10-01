package com.example.paymentsapplication.services.StudentService;

import com.example.paymentsapplication.dto.StudentDTO;
import com.example.paymentsapplication.entites.Student;
import com.example.paymentsapplication.mapper.StudentMapper;
import com.example.paymentsapplication.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor @NoArgsConstructor
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> studentList=studentRepository.findAll();
        List<StudentDTO> studentDTOList=studentList.stream().map(student ->studentMapper.fromStudent(student))
                .collect(Collectors.toList());
        return studentDTOList;
    }

    @Override
    public StudentDTO findStudentByCode(String code) {
        Student student=studentRepository.findByCode(code);
        StudentDTO studentDTO=studentMapper.fromStudent(student);
        return studentDTO;
    }

    @Override
    public List<StudentDTO> findStudentByProgramId(String programId) {
        List<Student> studentList=studentRepository.findByProgramId(programId);
        List<StudentDTO> studentDTOList=studentList.stream().map(student -> studentMapper.fromStudent(student))
                .collect(Collectors.toList());
        return studentDTOList;
    }
}
