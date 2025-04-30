package com.example.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookstoreRepo;

@Service
public class BookstoreService {

  //List<Book>books=new ArrayList<>();

  @Autowired
  BookstoreRepo repo;

  public List<Book> getBooks(){
    return repo.findAll();
  }

  public void addBook(Book book) {
    repo.save(book);
    //books.add(book);
  }


}
