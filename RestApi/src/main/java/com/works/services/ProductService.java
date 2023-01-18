package com.works.services;

import com.works.entities.Product;
import com.works.repositoies.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    // Product Save
    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Product> optionalProduct = productRepository.findByTitleEqualsIgnoreCase(product.getTitle());
        if (optionalProduct.isPresent() ) {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Insert Fail - constraint :" + product.getTitle());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }else {
            productRepository.save(product);
            hm.put(REnum.status, true);
            hm.put(REnum.message, "Insert Success");
            hm.put(REnum.result, product);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
    }


    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, productRepository.findAll() );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity delete( String stPid ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Long pid = Long.parseLong(stPid);
            productRepository.deleteById(pid);
            hm.put(REnum.status, true);
            hm.put(REnum.message, "Delete Success PID: " + stPid);
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            if ( ex.getMessage().contains("No class") ) {
                hm.put(REnum.message, "No Pid with exists!:"+ stPid);
            }

            if ( ex.getMessage().contains("For input string") ) {
                hm.put(REnum.message, "Convert Long Type Error: " + stPid);
            }
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update( Product product ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        boolean status = productRepository.existsByPidEquals(product.getPid());
        if ( status ) {
            productRepository.saveAndFlush(product);
            hm.put(REnum.status, true);
            hm.put(REnum.result, product);
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "No Product Row PID: " + product.getPid() );
        }

        return new ResponseEntity(hm, HttpStatus.OK);
    }


}