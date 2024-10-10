package com.library.mng.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table (name = "loan")
public class LoanModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userModel_id")
    private UserModel userModel ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookModel_id")
    private BookModel bookModel;

    @Column
    private Date created_at;

    @Column
    private Date returned_at;

}
