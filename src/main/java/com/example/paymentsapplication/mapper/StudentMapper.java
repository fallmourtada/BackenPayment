package com.example.paymentsapplication.mapper;

import com.example.paymentsapplication.dto.StudentDTO;
import com.example.paymentsapplication.entites.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentMapper {
    public Student fromStudentDTO(StudentDTO studentDTO){
        Student student=new Student();
        BeanUtils.copyProperties(studentDTO,student);
        return student;
    }

    public StudentDTO fromStudent(Student student){
        StudentDTO studentDTO=new StudentDTO();
        BeanUtils.copyProperties(student,studentDTO);
        return studentDTO;
    }
}
