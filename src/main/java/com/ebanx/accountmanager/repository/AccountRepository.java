package com.ebanx.accountmanager.repository;

import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {
    private final List<Account> accounts;
    @Autowired
    public AccountRepository(){
        this.accounts = new ArrayList<>();
    }

    private Account getAccount(Integer accountId){
       return accounts.stream().filter(account-> account.getAccountId().equals(accountId)).toList().getFirst();
    }
    public boolean isExistentAccount(Integer accountId){
        return accounts.stream().anyMatch(account -> account.getAccountId().equals(accountId));
    }

    public Double getBalance(Integer accountId){
        Account account = getAccount(accountId);
        return account.getBalance().doubleValue();
    }

    public Account deposit(Integer accountId, BigDecimal amount){
        Account destination;
        if(!isExistentAccount(accountId)){
            destination = Account.builder().accountId(accountId).balance(amount).build();
            accounts.add(destination);
        }else{
            destination = getAccount(accountId);
            destination.depositOperation(amount);
        }
        return destination;
    }

    public Account withdraw(Integer accountId, BigDecimal amount) throws AccountTransactionException {
        if(isExistentAccount(accountId)){
            Account origin = getAccount(accountId);
            origin.withdrawOperation(amount);
            return origin;
        }else{
            throw new AccountTransactionException(HttpStatus.NOT_FOUND,String.format("The account with id %d doesn't exists",accountId));
        }
    }

    public Map<String,Account> transfer(Integer origin, Integer destination, BigDecimal amount) throws AccountTransactionException {
        Account fromAccount = withdraw(origin,amount);
        Account toAccount = deposit(destination,amount);
        Map<String,Account> result = new HashMap<>();
        result.put("origin",fromAccount);
        result.put("destination",toAccount);
        return result;
    }


}
