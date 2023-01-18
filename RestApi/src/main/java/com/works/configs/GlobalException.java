package com.works.configs;

import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, parseError(ex.getFieldErrors()) );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadable( HttpMessageNotReadableException ex ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, ex.getMessage() );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

    private List parseError(List<FieldError> fieldErrors) {
        List list = new ArrayList();
        for( FieldError error : fieldErrors ) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            Map<String, String> hm = new HashMap<>();
            hm.put("field", field);
            hm.put("message", message);
            list.add(hm);
        }
        return list;
    }


}
