package com.library.mng.service;

import com.library.mng.DTO.RecordLoan;
import com.library.mng.model.BookModel;
import com.library.mng.model.LoanModel;
import com.library.mng.model.UserModel;
import com.library.mng.repository.BooksRepository;
import com.library.mng.repository.LoanRepository;
import com.library.mng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UserRepository userRepository;

    public LoanModel save(RecordLoan body) {
        LoanModel loan = new LoanModel();
        BookModel book = booksRepository.findById(body.book_id()).orElse(null);
        UserModel user = userRepository.findById(body.user_id()).orElse(null);
        if ((book == null || user == null)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Book or User not found.");
        }
        loan.setUserModel(user);
        loan.setBookModel(book);
        loan.setCreated_at(body.created_at());
        loan.setReturned_at(body.returned_at());

        return repository.save(loan);
    }

    public List<LoanModel> listAllLoans() {
        return repository.findAll();
    }

    public LoanModel getLoanById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LoanModel updateLoan (Long id, RecordLoan body){
        LoanModel loan = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found with id " + id));
        BookModel book = booksRepository.findById(body.book_id()).orElse(null);
        UserModel user = userRepository.findById(body.user_id()).orElse(null);
        if ((book == null || user == null)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Book or User not found.");
        }
        loan.setUserModel(user);
        loan.setBookModel(book);
        loan.setCreated_at(body.created_at());
        loan.setReturned_at(body.returned_at());

        return repository.save(loan);
    }

    public void deleteLoanById(Long id) {
        repository.deleteById(id);
    }
}
