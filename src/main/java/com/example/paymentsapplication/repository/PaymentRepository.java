package com.example.paymentsapplication.repository;

import com.example.paymentsapplication.entites.Payment;
import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);

    List<Payment> findByType(PaymentType type);

    List<Payment> findByStatus(PaymentStatus status);
}
