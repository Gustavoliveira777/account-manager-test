package com.ebanx.accountmanager.service.impl;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.model.Account;
import com.ebanx.accountmanager.repository.AccountRepository;
import com.ebanx.accountmanager.service.AccountManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.ebanx.accountmanager.enumerator.TypeActionEnumerator.*;

@Service
@Slf4j
public class AccountManagerServiceImpl implements AccountManagerService {
    @Autowired
    private AccountRepository repository;
    @Override
    public EventResponseDTO eventHandler(EventRequestDTO request) throws AccountTransactionException {
            if(request.getType() == DEPOSIT){
                log.info("DEPOSIT: Start Process | Received request: {}",request);
                Account result = repository.deposit(request.getDestination(),request.getAmount());
                EventResponseDTO response = EventResponseDTO.builder().destination(result).build();
                log.info("DEPOSIT: End of Process | Generated response: {}",response);
                return response;
            }else if(request.getType() == TRANSFER) {
                log.info("TRANSFER: Start Process | Received request: {}",request);
                Map<String, Account> result = repository.transfer(request.getOrigin(), request.getDestination(), request.getAmount());
                EventResponseDTO response = EventResponseDTO.builder().origin(result.get("origin")).destination(result.get("destination")).build();
                log.info("TRANSFER: End of Process | Generated response: {}",response);
                return response;
            }else if(request.getType() == WITHDRAW) {
                log.info("WITHDRAW: Start Process | Received request: {}",request);
                Account result = repository.withdraw(request.getOrigin(), request.getAmount());
                EventResponseDTO response = EventResponseDTO.builder().origin(result).build();
                log.info("WITHDRAW: End of Process | Generated response: {}",response);
                return response;
            }
        return null;
    }

    @Override
    public Double getBalance(Integer accountNumber) {
        return null;
    }
}
