package com.transaction.transaction.exceptions;


import com.transaction.transaction.payLoad.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class myGlobalExceptionsHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>  myMethodArgumentNotValidException(MethodArgumentNotValidException e){
             
        
        Map<String,String> response = new HashMap<>();
        
        e.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName =  ((FieldError)error).getField();
            String message =  error.getDefaultMessage();
            
            response.put(fieldName,message);
        });
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>>  handleRuntimeException(RuntimeException e){


        Map<String,String> response = new HashMap<>();

        response.put("timeStamp", LocalDateTime.now().toString());
        response.put("status",HttpStatus.BAD_REQUEST.toString());
        response.put("error","Bad Request");
        response.put("message",e.getMessage());


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<APIResponse>  myResourceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponse  apiresponse = new APIResponse(message,false);
        return new ResponseEntity<>(apiresponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(APIExceptions.class)
    public  ResponseEntity<APIResponse>  myAPIExceptions(APIExceptions e){
        String message = e.getMessage();
        APIResponse  apiresponse = new APIResponse(message,false);
        return new ResponseEntity<>(apiresponse, HttpStatus.BAD_REQUEST);
    }


    
}
