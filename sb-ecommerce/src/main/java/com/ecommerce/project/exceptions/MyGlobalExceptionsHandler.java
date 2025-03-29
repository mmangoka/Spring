package com.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*all logic to customize messages when exceptions occur*/
/*specialized version Rest Controller advice ,interceptor*/
/*exception handler handles exceptions*/
@RestControllerAdvice
public class MyGlobalExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message =  error.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String>  myResourceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
    return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }



    /*Handle items already existing e.g category */
    @ExceptionHandler(APIExceptions.class)
    public ResponseEntity<String>  myAPIExceptions(APIExceptions e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }
}
