package com.example.paymentsapplication.controller;

import com.example.paymentsapplication.dto.NewPaymentDTO;
import com.example.paymentsapplication.dto.PaymentDTO;
import com.example.paymentsapplication.entites.Payment;
import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import com.example.paymentsapplication.repository.PaymentRepository;
import com.example.paymentsapplication.services.PaymentService.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class PaymentController {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @GetMapping("/payments")
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/payments/{paymentId}")
    public PaymentDTO getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/students/{code}/payments")
    public List<PaymentDTO> getPaymentsByCode(@PathVariable String code) {
        return paymentService.paymentsByCode(code);
    }

    @GetMapping("/payments/byStatus")
    public List<PaymentDTO> getPaymentsByStatus(@RequestParam PaymentStatus status){
        return paymentService.paymentsByStatus(status);
    }

    @GetMapping("/payments/byType")
    public List<PaymentDTO> getPaymentsByType(@RequestParam PaymentType type){
        return paymentService.paymentsByType(type);
    }

    @PutMapping("/payments/{paymentId}")
    public PaymentDTO updateStatusPayments(@PathVariable Long paymentId, @RequestParam PaymentStatus status) {
        return paymentService.updatePaymentStatus(paymentId, status);
    }

    @PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PaymentDTO savePayment(@RequestParam  MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException{
        return paymentService.savePayment(file, newPaymentDTO);
    }



    @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }

}
