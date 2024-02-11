package com.ebanx.accountmanager.repository;

import com.ebanx.accountmanager.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        return accounts.stream().allMatch(account -> account.getAccountId().equals(accountId));
    }

    public Double getBalance(Integer accountId){
        Account account = getAccount(accountId);
        return account.getBalance().doubleValue();
    }

    public Account deposit(Integer accountId, Double amount){
        Account destination;
        if(!isExistentAccount(accountId)){
            destination = Account.builder().accountId(accountId).balance(new BigDecimal(amount)).build();
            accounts.add(destination);
        }else{
            destination = getAccount(accountId);
            destination.depositOperation(amount);
        }
        return destination;
    }

    public Account withdraw(Integer accountId, Double amount){
        Account destination = getAccount(accountId);
        destination.withdrawOperation(amount);
        return destination;
    }

    public Account transfer(Integer origin, Integer destination, Double amount){
        Account fromAccount = getAccount(origin);
        Account toAccount = getAccount(destination);
        fromAccount.withdrawOperation(amount);
        toAccount.depositOperation(amount);
        return toAccount; //TODO: Ajustar para devolver duas contas.
    }


}
