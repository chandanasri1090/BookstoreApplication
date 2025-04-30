package com.example.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.Book;

@Repository
public interface BookstoreRepo extends JpaRepository<Book,Integer>{

}
