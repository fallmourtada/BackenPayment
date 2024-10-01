package com.example.paymentsapplication.mapper;

import com.example.paymentsapplication.dto.PaymentDTO;
import com.example.paymentsapplication.entites.Payment;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentMapper {
    private StudentMapper studentMapper;

    public Payment fromPaymentDTO(PaymentDTO paymentDTO){
        Payment payment=new Payment();
        BeanUtils.copyProperties(paymentDTO,payment);
        payment.setStudent(studentMapper.fromStudentDTO(paymentDTO.getStudentDTO()));
        return payment;
    }

    public PaymentDTO fromPayment(Payment payment){
        PaymentDTO paymentDTO=new PaymentDTO();
        BeanUtils.copyProperties(payment,paymentDTO);
        paymentDTO.setStudentDTO(studentMapper.fromStudent(payment.getStudent()));
        return paymentDTO;
    }
}
