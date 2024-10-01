package com.example.paymentsapplication.dto;

import com.example.paymentsapplication.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter  @AllArgsConstructor @NoArgsConstructor
public class NewPaymentDTO {
    private double amount;

    private PaymentType type;

    private LocalDate date;

    private String studentCode;
}
