package com.example.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.Cart;
import com.example.bookstore.models.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
    Optional<Cart> findByUser(Users user); 
}
