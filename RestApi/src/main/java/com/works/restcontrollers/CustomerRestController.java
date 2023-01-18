package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid Customer customer) {
        return customerService.register(customer);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid Customer customer) {
        return customerService.login(customer);
    }

    @GetMapping("/loginError")
    public ResponseEntity loginError() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.message, "Please Login");
        return new ResponseEntity(hm, HttpStatus.UNAUTHORIZED);
    }


}
