package com.works.repositoies;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByTitleEqualsIgnoreCase(String title);

    // select * from product where UPPER(title) = UPPER(?)
    // Optional<Product> findByTitleEquals(String email);

}