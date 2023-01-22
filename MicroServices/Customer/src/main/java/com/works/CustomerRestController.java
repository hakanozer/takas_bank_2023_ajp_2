package com.works;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    final DiscoveryClient discoveryClient;

    @GetMapping("/info")
    public Map info() {
        Map hm = new LinkedHashMap();

        // Dicover Client
        List<ServiceInstance> list = discoveryClient.getInstances("product");
        ServiceInstance instance = list.get(0);
        String productUrl = instance.getUri().toString();

        productUrl = productUrl + "/product/list";
        RestTemplate restTemplate = new RestTemplate();
        Product productData = restTemplate.getForObject(productUrl, Product.class);

        hm.put("name", "Ali");
        hm.put("surname", "Bilmem");
        hm.put("productData", productData);
        return hm;
    }

}
