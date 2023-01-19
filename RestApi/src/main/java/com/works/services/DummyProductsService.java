package com.works.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.works.entities.Product;
import com.works.props.DummyProducts;
import com.works.props.DummyProductsGson;
import com.works.props.JWTLogin;
import com.works.props.Login;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DummyProductsService {

    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    public ResponseEntity products() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        String url = "https://dummyjson.com/products";
        //DummyProducts data = restTemplate.getForObject(url, DummyProducts.class);
        String data = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        DummyProductsGson productsGson = gson.fromJson(data, DummyProductsGson.class);

        // Java to Json
        Product product = new Product();
        product.setPid(100l);
        product.setDetail("Detail");
        product.setTitle("Macbook Pro");
        product.setPrice(75000);
        String objStr = null;
        try {
            objStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(objStr);
        // HttpHeaders
        // HttpEntity

        hm.put(REnum.status, false);
        hm.put(REnum.result, productsGson.getProducts() );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity login() {

        String url = "https://dummyjson.com/auth/login";
        Login login = new Login();
        login.setUsername("kminchelle");
        login.setPassword("0lelplR");
        String sendData = "";
        try {
            sendData = objectMapper.writeValueAsString(login);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(sendData, httpHeaders);
        ResponseEntity<JWTLogin> dt = restTemplate.postForEntity(url, httpEntity, JWTLogin.class );
        System.out.println( dt.getBody().getToken() );
        return dt;
    }


}
