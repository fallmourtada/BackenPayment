package com.example.paymentsapplication.services.PaymentService;

import com.example.paymentsapplication.dto.NewPaymentDTO;
import com.example.paymentsapplication.dto.PaymentDTO;
import com.example.paymentsapplication.entites.Payment;
import com.example.paymentsapplication.entites.Student;
import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import com.example.paymentsapplication.mapper.PaymentMapper;
import com.example.paymentsapplication.repository.PaymentRepository;
import com.example.paymentsapplication.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private PaymentRepository paymentRepository;

    private StudentRepository studentRepository;

    private PaymentMapper paymentMapper;

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments=paymentRepository.findAll();
        List<PaymentDTO> paymentDTOList= payments.stream().map(payment -> paymentMapper.fromPayment(payment))
                .collect(Collectors.toList());
        return paymentDTOList;
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
       Payment payment= paymentRepository.findById(paymentId).get();
        return paymentMapper.fromPayment(payment);
    }

    @Override
    public List<PaymentDTO> paymentsByCode(String code) {
        List<Payment> paymentList=paymentRepository.findByStudentCode(code);
        List<PaymentDTO> paymentDTOList=paymentList.stream().map(payment -> paymentMapper.fromPayment(payment))
                .collect(Collectors.toList());
        return paymentDTOList;
    }

    @Override
    public List<PaymentDTO> paymentsByStatus(PaymentStatus status) {
        List<Payment> payments=paymentRepository.findByStatus(status);
        List<PaymentDTO> paymentDTOList=payments.stream().map(payment -> paymentMapper.fromPayment(payment))
                .collect(Collectors.toList());
        return paymentDTOList;
    }

    @Override
    public List<PaymentDTO> paymentsByType(PaymentType type) {
        List<Payment> payments=paymentRepository.findByType(type);
        List<PaymentDTO> paymentDTOList=payments.stream().map(payment -> paymentMapper.fromPayment(payment))
                .collect(Collectors.toList());
        return paymentDTOList;
    }

    @Override
    public PaymentDTO updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment=paymentRepository.findById(paymentId).get();
        payment.setStatus(status);
        Payment payment1=paymentRepository.save(payment);
        return paymentMapper.fromPayment(payment1);
    }

    @Override
    public PaymentDTO savePayment(MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException {

        //Verification de lexistence du dossier sinon on le cree
        //Creation du dossier payments dans le dossier enset-data
        Path folderPath=Paths.get(System.getProperty("user.home"),"enset-data","payments");
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        Student student=studentRepository.findByCode(newPaymentDTO.getStudentCode());
        //Creation d'un fichier qui va contenir le fichier envoyer par l'utilistateur
        String fileName=UUID.randomUUID().toString();

        //Ajout du fichier dans le dossier payments
        Path filePath=Paths.get(System.getProperty("user.home"),"enset-data","payments",fileName+".pdf");

        //copier le fichier envoyer par l'utilisateur dans le fichier creer
        Files.copy(file.getInputStream(), filePath);

        Payment payment=Payment.builder()
                .date(newPaymentDTO.getDate())
                .amount(newPaymentDTO.getAmount())
                .type(newPaymentDTO.getType())
                .student(student)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();

            Payment payment1=paymentRepository.save(payment);

        PaymentDTO paymentDTO=paymentMapper.fromPayment(payment1);
        return paymentDTO;

    }
}
