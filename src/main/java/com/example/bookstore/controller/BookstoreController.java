package com.example.bookstore.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.models.Book;
import com.example.bookstore.services.BookstoreService;

@RestController
@RequestMapping("/bookstore/books")
public class BookstoreController {

  @Autowired
  BookstoreService bookstoreService;

  @GetMapping("/getBooks")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<List<Book>> getBooks() {
    List<Book> books = bookstoreService.getBooks();
    return ResponseEntity.ok(books);
  }

  @GetMapping("/getBookByName")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<List<Book>> getBookByName(@RequestBody String name) {
    System.out.println("Hit the getBookByName api");
    List<Book> books = bookstoreService.getBookDetailsByName(name);
    return ResponseEntity.ok(books);
  }

  @PutMapping("/updateBook/{id}")
  @PreAuthorize("hasAuthority('BOOK_WRITE')")
  public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
    Book updateBook = bookstoreService.updateBook(id, book);
    if (Objects.nonNull(updateBook)) {
      return ResponseEntity.ok(updateBook);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/addBook")
  @PreAuthorize("hasAuthority('BOOK_WRITE')")
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    bookstoreService.addBook(book);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/deleteBook/{id}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
    Book deletedBook = bookstoreService.deleteBook(id);
    if (Objects.nonNull(deletedBook)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  

}
