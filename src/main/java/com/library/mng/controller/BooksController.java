package com.library.mng.controller;

import com.library.mng.DTO.RecordBook;
import com.library.mng.model.BookModel;
import com.library.mng.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService service;

    @PostMapping()
    public BookModel addBook(@RequestBody RecordBook book) {
       return service.save(book);
   }

   @GetMapping()
   public List<BookModel> listAll (){
        return service.listAll();
   }

   @GetMapping("/{id}")
    public BookModel findById(@PathVariable("id") Long id) {

       return service.findById(id);
   }

   @PostMapping("/books/{id}")
   public BookModel changed (@PathVariable Long id, @RequestBody RecordBook book){
       return service.update( id, book);
   }

   @DeleteMapping("/{id}")
   public void delete (@PathVariable Long id ){
     service.delete(id);
   }

}
