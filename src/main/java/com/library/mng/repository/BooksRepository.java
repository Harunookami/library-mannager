package com.library.mng.repository;

import com.library.mng.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository <BookModel, Long> {


}
