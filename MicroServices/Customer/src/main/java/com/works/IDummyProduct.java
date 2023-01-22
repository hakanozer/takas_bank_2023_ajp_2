package com.works;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dummyjson", url = "https://dummyjson.com/")
public interface IDummyProduct {

    @GetMapping("/products")
    DummyProducts products();

}
