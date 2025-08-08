package com.example.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.models.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{

}
