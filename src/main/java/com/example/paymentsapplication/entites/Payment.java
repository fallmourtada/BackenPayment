package com.example.paymentsapplication.entites;

import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private double amount;

    private PaymentType type;

    private PaymentStatus status;

    private String file;

    @ManyToOne
    private Student student;

}
