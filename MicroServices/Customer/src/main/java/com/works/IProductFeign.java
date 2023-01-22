package com.works;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "product")
public interface IProductFeign {

    @GetMapping("/product/list")
    Object productList();

    @PostMapping("/product/save")
    Object productSave(SendProduct product);

}
