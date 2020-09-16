package com.java.jwt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_TBL", uniqueConstraints = @UniqueConstraint(columnNames={"email"}))
public class User {

    @Id
    @SequenceGenerator(initialValue = 1, name = "user_seq", allocationSize = 1, sequenceName = "user_seq")
    @GeneratedValue(generator = "user_seq")
    private int id;
    private String username;
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
