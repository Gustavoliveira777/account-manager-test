package com.ebanx.accountmanager.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class AccountTransactionException extends Exception{
    private HttpStatus returnStatusCode;
    public AccountTransactionException(HttpStatus returnStatusCode, String message){
        super(message);
        this.returnStatusCode = returnStatusCode;
    }
}
