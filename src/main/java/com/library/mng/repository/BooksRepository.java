package com.library.mng.repository;

import com.library.mng.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository <BookModel, Long> {
    Optional<BookModel> findByTitleAndAuthor(String title, String author);


}
