package com.example.paymentsapplication;

import com.example.paymentsapplication.entites.Payment;
import com.example.paymentsapplication.entites.Student;
import com.example.paymentsapplication.enums.PaymentStatus;
import com.example.paymentsapplication.enums.PaymentType;
import com.example.paymentsapplication.repository.PaymentRepository;
import com.example.paymentsapplication.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor
public class PaymentsApplication {
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
										PaymentRepository paymentRepository,
										JdbcUserDetailsManager jdbcUserDetailsManager){
		return args->{
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
							.firstName("Mourtada").lastName("Fall").programId("SDIA").code("12347")
					.build());

			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Fallou").lastName("Fall").programId("SDIA").code("12345")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Cheikhouna").lastName("Fall").programId("SDIA").code("12346")
					.build());

			studentRepository.findAll().forEach(student -> {
				for(int i=0; i<10; i++){
					Payment payment=new Payment();
					payment.setAmount(Math.random()*2000);
					payment.setStatus(PaymentStatus.CREATED);
					payment.setType(PaymentType.DEPOSIT);
					payment.setStudent(student);
					payment.setDate(LocalDate.now());
					paymentRepository.save(payment);
				}

			});

			UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user");
			if(u1==null)
				jdbcUserDetailsManager.createUser(
						User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build()
				);
			UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("admin");
			if(u2==null)
				jdbcUserDetailsManager.createUser(
						User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN").build()
				);

		};
	}




}
