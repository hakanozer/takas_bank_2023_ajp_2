package com.works;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    final NoteRepository noteRepository;
    final IProductFeign iProductFeign;
    final IDummyProduct iDummyProduct;

    @HystrixCommand(fallbackMethod = "fallBack")
    @GetMapping("/info")
    public Map info() {
        Map hm = new LinkedHashMap();

        // int i = 1 / 0;
        // Dicover Client
        List<ServiceInstance> list = discoveryClient.getInstances("product");
        if ( list != null && list.size() > 0 ) {

            ServiceInstance instance = list.get(0);
            String productUrl = instance.getUri().toString();

            productUrl = productUrl + "/product/list";
            RestTemplate restTemplate = new RestTemplate();
            Product productData = restTemplate.getForObject(productUrl, Product.class);

            Note note = new Note();
            note.setTitle("New Note Title");
            noteRepository.save(note);

            hm.put("name", "Ali");
            hm.put("surname", "Bilmem");
            hm.put("productData", productData);
        }

        return hm;
    }

    public Map fallBack() {
        Map hm = new LinkedHashMap();
        hm.put("status", "false");
        hm.put("message", "Service Fail, Try Again");
        return hm;
    }

    @GetMapping("/note")
    public Map note() {
        Map hm = new LinkedHashMap();
        hm.put("result", noteRepository.findAll());
        return hm;
    }

    @GetMapping("/infoFein")
    public Map infoFein() {
        Map hm = new LinkedHashMap();
        hm.put("result", iProductFeign.productList() );
        return hm;
    }

    @GetMapping("/saveFein")
    public Map saveFein() {
        Map hm = new LinkedHashMap();
        SendProduct product = new SendProduct();
        product.setPid(200l);
        product.setTitle("Tablet");
        hm.put("result", iProductFeign.productSave(product) );
        return hm;
    }

    @GetMapping("/dummyProduct")
    public DummyProducts dummyProduct() {
        DummyProducts dummyProducts = iDummyProduct.products();
        return dummyProducts;
    }

}
