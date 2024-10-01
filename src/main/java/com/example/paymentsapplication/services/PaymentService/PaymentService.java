package com.example.paymentsapplication.services.PaymentService;

import com.example.paymentsapplication.dto.NewPaymentDTO;
import com.example.paymentsapplication.dto.PaymentDTO;
import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    List<PaymentDTO> getAllPayments();

    PaymentDTO getPaymentById(Long paymentId);

    List<PaymentDTO> paymentsByCode(String code);

    List<PaymentDTO> paymentsByStatus(PaymentStatus status);

    List<PaymentDTO> paymentsByType(PaymentType type);

    PaymentDTO updatePaymentStatus(Long paymentId, PaymentStatus status);

    PaymentDTO savePayment(MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException;
}
