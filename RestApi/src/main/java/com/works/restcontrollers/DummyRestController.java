package com.works.restcontrollers;

import com.works.services.DummyProductsService;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
public class DummyRestController {

    final DummyProductsService dummyProductsService;

    @GetMapping("/products")
    public ResponseEntity products() {
        return dummyProductsService.products();
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        return dummyProductsService.login();
    }

}
