package com.works.services;

import com.works.entities.Product;
import com.works.repositoies.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    // Product Save
    public ResponseEntity save(Product product) {
        // insert into product( default, title, detail, price )
       productRepository.save(product);
       return new ResponseEntity(product, HttpStatus.OK);
    }

}

/*
title
price
detail
pid
 */
