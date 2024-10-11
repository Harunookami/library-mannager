package com.library.mng.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table (name = "loan")
public class LoanModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userModel_id")
    private UserModel userModel ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookModel_id")
    private BookModel bookModel;

    @Column (nullable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(nullable = true)
    private Date returned_at;

}
