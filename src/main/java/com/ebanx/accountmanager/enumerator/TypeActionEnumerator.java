package com.ebanx.accountmanager.enumerator;

import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum TypeActionEnumerator {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    TRANSFER("transfer");

    private final String value;
    TypeActionEnumerator(String value){
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TypeActionEnumerator fromValue(String value) throws AccountTransactionException {
        for(TypeActionEnumerator item: TypeActionEnumerator.values()){
            if(item.getValue().equals(value)){
                return item;
            }
        }
        try{
            return TypeActionEnumerator.valueOf(value);
        }catch (IllegalArgumentException e){
            throw new AccountTransactionException(HttpStatus.BAD_REQUEST,"The informed event type doesn't exists",e);
        }
    }

}
