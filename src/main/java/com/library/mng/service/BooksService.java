package com.library.mng.service;

import com.library.mng.DTO.RecordBook;
import com.library.mng.model.BookModel;
import com.library.mng.repository.BooksRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;

    @Transactional
    public BookModel saveOrUpdateBook (RecordBook body) {
        Optional<BookModel> existingBook = repository.findByTitleAndAuthor(body.title(), body.author());
        if (existingBook.isPresent()) {
            BookModel book = existingBook.get();
            book.setAmount(book.getAmount() + 1);
            return repository.save(book);
        } else {
            BookModel newBook = new BookModel ();
            newBook.setTitle(body.title());
            newBook.setAuthor(body.author());
            newBook.setPublisher(body.publisher());
            newBook.setAmount(1);
            return repository.save(newBook);
        }
    }

    public List<BookModel> listAll(){
        return repository.findAll();
    }

    public BookModel findById(Long id) {
        return repository.findById(id).orElse(null);

    }


    public BookModel update(Long id, RecordBook body) {
        BookModel book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        book.setAuthor(body.author());
        book.setTitle(body.title());
        book.setPublisher(body.publisher());
        return repository.save(book);
    }

    public void delete(Long id) {
        BookModel book = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        if (book.getAmount() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than zero");
        }
        book.decreaseAmount();
        repository.save(book);
    }

}
