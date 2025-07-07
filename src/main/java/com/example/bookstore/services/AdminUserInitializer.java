package com.example.bookstore.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.bookstore.models.Users;
import com.example.bookstore.repositories.UserDetailsRepository;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createUser(UserDetailsRepository userDetailsRepository,PasswordEncoder passwordEncoder){
        return args->{
            if(userDetailsRepository.findByUserName("admin").isEmpty()){
                Users user=new Users();
                user.setUserName("admin");
                user.setPassword(passwordEncoder.encode("admin123"));
                user.setRole("ROLE_ADMIN");
                userDetailsRepository.save(user);
                System.err.println("Default User created");
            }

        };

    }

}
