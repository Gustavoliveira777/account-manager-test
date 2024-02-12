package com.ebanx.accountmanager.service.impl;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.model.Account;
import com.ebanx.accountmanager.repository.AccountRepository;
import com.ebanx.accountmanager.service.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.ebanx.accountmanager.enumerator.TypeActionEnumerator.*;

@Service
public class AccountManagerServiceImpl implements AccountManagerService {
    @Autowired
    private AccountRepository repository;
    @Override
    public EventResponseDTO eventHandler(EventRequestDTO request) throws AccountTransactionException {
            if(request.getType() == DEPOSIT){
                Account result = repository.deposit(request.getDestination(),request.getAmount());
                return EventResponseDTO.builder().destination(result).build();
            }else if(request.getType() == TRANSFER) {
                Map<String, Account> result = repository.transfer(request.getOrigin(), request.getDestination(), request.getAmount());
                return EventResponseDTO.builder().origin(result.get("origin")).destination(result.get("destination")).build();
            }else if(request.getType() == WITHDRAW) {
                Account result = repository.withdraw(request.getOrigin(), request.getAmount());
                return EventResponseDTO.builder().origin(result).build();
            }
        return null;
    }

    @Override
    public Double getBalance(Integer accountNumber) {
        return null;
    }
}
