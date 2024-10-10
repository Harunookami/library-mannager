package com.library.mng.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table (name = "books")

public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @Column (length = 120, nullable = false)
    private String title;

    @Column (length = 120, nullable = false)
    private String author;

    @Column (length = 120, nullable = false)
    private String publisher;

    @OneToMany(mappedBy = "bookModel", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<LoanModel> loans;
}

