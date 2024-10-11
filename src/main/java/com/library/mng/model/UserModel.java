package com.library.mng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table (name = "users")

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column(length = 124, nullable = false)
    private String fullName;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 11, nullable = false)
    private String phone;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @OneToMany(mappedBy = "userModel", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<LoanModel> loans;
}
