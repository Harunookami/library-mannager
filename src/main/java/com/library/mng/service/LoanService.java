package com.library.mng.service;

import com.library.mng.DTO.RecordLoan;
import com.library.mng.model.BookModel;
import com.library.mng.model.LoanModel;
import com.library.mng.model.UserModel;
import com.library.mng.repository.BooksRepository;
import com.library.mng.repository.LoanRepository;
import com.library.mng.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UserRepository userRepository;

    public LoanModel save(RecordLoan recordLoan) {
        BookModel book = getBookById(recordLoan.bookId());
        UserModel user = getUserById(recordLoan.userId());

        if (!areCreateLoanPreRequisitesValid(user, book)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan pre-requisites error. User does not exist or book is unavailable.");
        }

        LoanModel loan = new LoanModel();
        loan.setUserModel(user);
        loan.setBookModel(book);

        book.decreaseAmount();
        booksRepository.save(book);

        return loanRepository.save(loan);

    }

    public List<LoanModel> listAllLoans() {
        return loanRepository.findAll();
    }

    public LoanModel getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found with id " + id));
    }

    public LoanModel updateLoan(Long id, RecordLoan recordLoan) {
        LoanModel loan = getLoanById(id);
        BookModel book = getBookById(recordLoan.bookId());
        UserModel user = getUserById(recordLoan.userId());

        if (!areUpdateLoanPreRequisitesValid(user, book)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan pre-requisites error. User does not exist or book is unavailable.");
        }

        loan.setUserModel(user);
        loan.setBookModel(book);

        return loanRepository.save(loan);
    }

    public void deleteLoanById(Long id) {
        LoanModel loan = getLoanById(id);

        if (loan == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found with id " + id);
        }

        BookModel book = loan.getBookModel ();
        book.increaseAmount();
        booksRepository.save(book);
        loanRepository.deleteById(id);
    }

    private BookModel getBookById(Long bookId) {
        return booksRepository.findById(bookId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id " + bookId));
    }

    private UserModel getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId));
    }

    private boolean isBookAvailable(BookModel book) {
        return book != null && book.isAvailable();
    }

    private boolean isUserValid(UserModel user) {
        return user != null;
    }

    private boolean areCreateLoanPreRequisitesValid(UserModel user, BookModel book) {
        return isUserValid(user) && isBookAvailable(book);
    }

    private boolean areUpdateLoanPreRequisitesValid(UserModel user, BookModel book) {
        return isUserValid(user) &&  book != null;
    }

    public LoanModel returnLoan(Long id, RecordLoan recordLoan) {
        BookModel book = getBookById(recordLoan.bookId());
        UserModel user = getUserById(recordLoan.userId());
        if (!areUpdateLoanPreRequisitesValid(user, book)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan pre-requisites error. User does not exist or book is unavailable.");
        }
        LoanModel loan = getLoanById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        loan.setReturned_at(timestamp);
        book.increaseAmount();
        booksRepository.save(book);
        return loanRepository.save(loan);
    }
}
