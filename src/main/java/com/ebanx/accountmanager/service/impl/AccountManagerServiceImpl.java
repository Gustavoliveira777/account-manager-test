package com.ebanx.accountmanager.service.impl;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.repository.AccountRepository;
import com.ebanx.accountmanager.service.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerServiceImpl implements AccountManagerService {
    @Autowired
    private AccountRepository repository;
    @Override
    public EventResponseDTO eventHandler(EventRequestDTO request) {
        switch (request.type()){
            case DEPOSIT:
                break;
            case TRANSFER:
                //todo
                break;
            case WITHDRAW:
                //todo
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Double getBalance(Integer accountNumber) {
        return null;
    }
}
