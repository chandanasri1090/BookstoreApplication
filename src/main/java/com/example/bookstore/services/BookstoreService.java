package com.example.bookstore.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookstoreRepo;

@Service
public class BookstoreService {

  @Autowired
  BookstoreRepo repo;

  public List<Book> getBooks() {
    return repo.findAll();
  }

  // @CachePut(value="book",key="#name")
  public void addBook(Book book) {
    repo.save(book);
  }

  // @CacheEvict(value = "book",key="#name")
  public Book deleteBook(Integer id) {
    return repo.findById(id).map(book -> {
      repo.delete(book);
      return book;
    }).orElse(null);
  }

  // @Cacheable(value = "book", key = "#name")
  public List<Book> getBookDetailsByName(String name) {
    return repo.findBookByName(name).stream().collect(Collectors.toList());
  }

  // @CachePut(value="book",key="#name")
  public Book updateBook(Integer id, Book updatedBook) {
    return repo.findById(id).map(book -> {
      book.setName(updatedBook.getName());
      book.setAuthorName(updatedBook.getAuthorName());
      book.setGenre(updatedBook.getGenre());
      book.setPrice(updatedBook.getPrice());
      book.setQuantity(updatedBook.getQuantity());
      repo.save(book);
      return book;
    }).orElse(null);
  }

}
