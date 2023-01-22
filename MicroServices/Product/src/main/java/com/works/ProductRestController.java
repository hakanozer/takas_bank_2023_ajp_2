package com.works;

import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @GetMapping("/list")
    public Map list() {
        Map<String, String> hm = new LinkedHashMap<>();
        hm.put("status", "true");
        hm.put("result", "product result");
        return hm;
    }

    @PostMapping("/save")
    public Map save(@RequestBody Product pro) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("result", pro);
        return hm;
    }


}
