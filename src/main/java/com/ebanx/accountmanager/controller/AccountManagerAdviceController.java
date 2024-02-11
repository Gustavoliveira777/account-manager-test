package com.ebanx.accountmanager.controller;

import com.ebanx.accountmanager.exception.AccountTransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountManagerAdviceController {
    @ExceptionHandler(AccountTransactionException.class)
    ResponseEntity<Integer> transactionErrorHandler(AccountTransactionException exception){
        exception.printStackTrace();
        return ResponseEntity.status(exception.getReturnStatusCode()).body(0);
    }
}
