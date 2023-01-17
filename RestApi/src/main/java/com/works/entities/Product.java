package com.works.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(unique = true, length = 100)
    private String title;

    @Column(length = 500)
    private String detail;

    private Integer price;


}
