package com.ebanx.accountmanager.repository;

import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class AccountRepository {
    private final List<Account> accounts;

    @Autowired
    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    private Account getAccount(Integer accountId) {
        return accounts.stream().filter(account -> account.getAccountId().equals(accountId)).toList().getFirst();
    }

    public boolean isExistentAccount(Integer accountId, boolean advice) throws AccountTransactionException {
        boolean matches = accounts.stream().anyMatch(account -> account.getAccountId().equals(accountId));
        if (!advice) {
            return matches;
        } else {
            if (!matches) {
                throw new AccountTransactionException(HttpStatus.NOT_FOUND, String.format("The account with id %d doesn't exists", accountId));
            } else {
                return matches;
            }
        }
    }

    public BigDecimal getBalance(Integer accountId) throws AccountTransactionException {
        isExistentAccount(accountId, true);
        Account account = getAccount(accountId);
        return account.getBalance();
    }

    public Account deposit(Integer accountId, BigDecimal amount) throws AccountTransactionException {
        Account destination;
        if (!isExistentAccount(accountId, false)) {
            log.info("The account with ID: {} doesn't exists. A new account with this ID will be created.", accountId);
            destination = Account.builder().accountId(accountId).balance(amount).build();
            accounts.add(destination);
            log.info("A new account with ID {} has been created. Account details: {}", destination.getAccountId(), destination);
        } else {
            destination = getAccount(accountId);
            log.info("Account found!. Account details: {}", destination);
            destination.depositOperation(amount);
            log.info("The account(ID: {}) had its balance changed. Account details {} ", destination.getAccountId(), amount);
        }
        log.info("Deposit operation return: {}", destination);
        return destination;
    }

    public Account withdraw(Integer accountId, BigDecimal amount) throws AccountTransactionException {
        isExistentAccount(accountId, true);
        Account origin = getAccount(accountId);
        log.info("Account found!. Account details: {}", origin);

        origin.withdrawOperation(amount);
        log.info("The account(ID: {}) had its balance changed. Account details {} ", origin.getAccountId(), origin);
        log.info("Withdraw operation return: {}", origin);
        return origin;
    }

    public Map<String, Account> transfer(Integer origin, Integer destination, BigDecimal amount) throws AccountTransactionException {
        Account fromAccount = withdraw(origin, amount);
        log.info("The amount was successfully withdrawn from the source account (ID:{}) and will be deposited in the destination account (ID:{}) ",fromAccount.getAccountId(),destination);
        Account toAccount = deposit(destination, amount);
        log.info("The amount was successfully deposited into the destination account (ID:{}) ",toAccount.getAccountId());

        Map<String, Account> result = new HashMap<>();
        result.put("origin", fromAccount);
        result.put("destination", toAccount);
        log.info("Transfer operation return: {}", result);
        return result;
    }


}
