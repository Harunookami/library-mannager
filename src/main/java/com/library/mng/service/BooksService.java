package com.library.mng.service;

import com.library.mng.DTO.RecordBook;
import com.library.mng.model.BookModel;
import com.library.mng.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;

    public BookModel save ( RecordBook body) {
       BookModel book = new BookModel();
       book.setAuthor(body.author());
       book.setTitle(body.title());
       book.setPublisher(body.publisher());

       return repository.save(book);

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
        repository.deleteById(id);
    }

}
