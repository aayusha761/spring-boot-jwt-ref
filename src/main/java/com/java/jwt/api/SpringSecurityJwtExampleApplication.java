package com.java.jwt.api;

import com.java.jwt.api.entity.Role;
import com.java.jwt.api.entity.User;
import com.java.jwt.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityJwtExampleApplication {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "aayush", "aayush", "aayush@lti.com", Role.ADMIN),
                new User(102, "sonu", "sonu", "sonu@lti.com", Role.USER)
        ).collect(Collectors.toList());
        repository.saveAll(users);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }

}
