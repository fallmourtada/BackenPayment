package com.example.paymentsapplication.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Student {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String code;

    private String programId;

    private String photo;
}
