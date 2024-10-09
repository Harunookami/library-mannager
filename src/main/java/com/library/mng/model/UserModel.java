package com.library.mng.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table (name = "users")

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long userId;

    @Column(length = 50, nullable = false)
    private String fullName;
    @Column(length = 11, nullable = false)
    private String cpf;
    @Column(length = 11, nullable = false)
    private String tel;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;
}
