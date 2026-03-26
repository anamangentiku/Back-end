package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

import com.example.demo.entity.User;
import com.example.demo.entity.Order;
import com.example.demo.entity.Student;
import com.example.demo.entity.Course;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepo,
                          StudentRepository studentRepo) {
        return args -> {

            // ========================
            // 🔵 ONE TO MANY (User - Order)
            // ========================
            User user = new User();
            user.setName("John");

            Order order1 = new Order();
            order1.setProduct("Laptop");
            order1.setUser(user);

            Order order2 = new Order();
            order2.setProduct("Mouse");
            order2.setUser(user);

            user.setOrders(Arrays.asList(order1, order2));

            userRepo.save(user); // otomatis save order juga (cascade)

            // ========================
            // 🟢 MANY TO MANY (Student - Course)
            // ========================
            Student student = new Student();
            student.setName("Budi");

            Course course1 = new Course();
            course1.setTitle("Math");

            Course course2 = new Course();
            course2.setTitle("Physics");

            student.setCourses(Arrays.asList(course1, course2));

            studentRepo.save(student);
        };
    }
}