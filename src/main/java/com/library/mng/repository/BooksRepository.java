package com.library.mng.repository;

import com.library.mng.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface BooksRepository extends JpaRepository <BookModel, Long> {


}
