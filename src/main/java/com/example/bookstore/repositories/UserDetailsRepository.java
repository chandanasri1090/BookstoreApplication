package com.example.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstore.models.Users;


public interface UserDetailsRepository  extends JpaRepository<Users,Long>{

    Optional<Users> findByUserName(String username);

}
