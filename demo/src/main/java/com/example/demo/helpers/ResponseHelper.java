package com.example.demo.helpers;


    import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ResponseHelper {
    
    public static <T, K> ResponseEntity<?> response(HttpStatus statusCode, Boolean ok, T data, K message) {
        Map<String, Object> response = new HashMap<>();
        response.put("ok", ok);
        response.put("data", data);
        response.put("message", message);
        return new ResponseEntity<>(response, statusCode);
    }

    public static ResponseEntity<?> validFields(BindingResult result){
        List<String> errors=result.getFieldErrors().stream().map(error->error.getDefaultMessage()).toList();

        return response(HttpStatus.BAD_REQUEST, false, "", errors);

    }

    public static ResponseEntity<?> catchResponse(Exception e){
        return response(HttpStatus.INTERNAL_SERVER_ERROR, false, "", e.getMessage());
    }
}
