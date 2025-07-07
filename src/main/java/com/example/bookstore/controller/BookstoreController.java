package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookstoreService;

@RestController
@RequestMapping("/bookstore")
public class BookstoreController {

  @Autowired
  BookstoreService bookstoreService;

  @GetMapping("/getBooks")
  public ResponseEntity<List<Book>> getBooks(){
    List<Book> books = bookstoreService.getBooks();
    return ResponseEntity.ok(books);
  }

  @PostMapping("/addBook")
  public ResponseEntity<Book> addBook(@RequestBody Book book){
    bookstoreService.addBook(book);
    return ResponseEntity.ok().build();

  }

  @PostMapping("/testBook")
  public ResponseEntity<String> testBook(@RequestBody Book book){
   return ResponseEntity.ok("Request reached the server");

  }
}
