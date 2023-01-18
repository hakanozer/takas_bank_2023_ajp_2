package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String name;

    @Column(unique = true)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Column(length = 500)
    @NotEmpty
    @NotNull
    private String password;

}
