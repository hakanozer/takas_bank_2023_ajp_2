package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid Product product) {
        return productService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list( @RequestParam(defaultValue = "0") int page ) {
        return productService.list(page);
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity delete( @PathVariable String pid ) {
        return productService.delete(pid);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid Product product) {
        return productService.update(product);
    }

}
