package com.works.services;

import com.works.entities.Customer;
import com.works.repositoies.CustomerRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest request;

    public ResponseEntity register(Customer customer) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            customer.setPassword(tinkEncDec.encrypt(customer.getPassword()));
            customerRepository.save(customer);
            hm.put(REnum.status, true);
            hm.put(REnum.result, customer);
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, ex.getMessage());
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity login(Customer customer) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            Customer cus = optionalCustomer.get();
            String dbPass = tinkEncDec.decrypt(cus.getPassword());
            if (dbPass.equals(customer.getPassword())) {
                hm.put(REnum.status, true);
                hm.put(REnum.message, "Login Success");
                // Session Create
                request.getSession().setAttribute("user", cus);
                return new ResponseEntity(hm, HttpStatus.OK);
            }
        }
        hm.put(REnum.status, false);
        hm.put(REnum.message, "Email or Password Fail");
        return new ResponseEntity(hm, HttpStatus.UNAUTHORIZED);
    }

}
