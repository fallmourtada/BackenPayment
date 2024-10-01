package com.example.paymentsapplication.dto;

import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDTO {
    private Long id;

    private LocalDate date;

    private double amount;

    private PaymentStatus status;

    private PaymentType type;

    private StudentDTO studentDTO;

    private String file;

}
