package com.example.springapi;

import com.example.springapi.entity.User;
import com.example.springapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CoworkingSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoworkingSpaceApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Create admin user if not exists
            if (!userRepository.findByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(User.Role.ADMIN);
                userRepository.save(admin);
            }

            // Create customer user if not exists
            if (!userRepository.findByUsername("customer")) {
                User customer = new User();
                customer.setUsername("customer");
                customer.setPassword(encoder.encode("customer123"));
                customer.setRole(User.Role.CUSTOMER);
                userRepository.save(customer);
            }
        };
    }
}