package com.library.mng.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userModel_id")
    @JsonManagedReference
    private UserModel userModel ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookModel_id")
    @JsonManagedReference
    private BookModel bookModel;

    @Column (nullable = false)
    @CreationTimestamp
    private Date created_at;

    @Column(nullable = true)
    private Date returned_at;

}
