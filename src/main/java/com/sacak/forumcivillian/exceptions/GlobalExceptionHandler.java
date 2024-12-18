package com.sacak.forumcivillian.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> runtimeExcepitonHandler(RuntimeException exception){
        log.error(exception.getMessage());
        return  createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,null);
    }

    @ExceptionHandler(ForumCivillianException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> forumCivillianExceptionHandler(ForumCivillianException exception){

        return createResponseEntity(exception.getErrorType(),exception.getErrorType().getHttpStatus(),null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        List<String> fieldErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(f->{

                    fieldErrors.add("Değişken adı..: "+ f.getField()+ " - Hata Detayı...: "+ f.getDefaultMessage());
                });
        return createResponseEntity(ErrorType.VALIDATION_ERROR,HttpStatus.BAD_REQUEST,fieldErrors);
    }



    public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType, HttpStatus httpStatus, List<String> fields){
        log.error("Something went wrong: "+ errorType.getMessage());
        if(fields!=null) log.error("Fields"+fields);
        return new ResponseEntity<>(ErrorMessage.builder()
                .fields(fields)
                .success(false)
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build(),httpStatus);
    }
}