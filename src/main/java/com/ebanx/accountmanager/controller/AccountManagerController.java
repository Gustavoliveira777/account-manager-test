package com.ebanx.accountmanager.controller;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.service.AccountManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountManagerController {
    @Autowired
    private AccountManagerService service;
    @PostMapping("/reset")
    public ResponseEntity<String> reset() throws AccountTransactionException{
        log.info("A database reset has been requested");
        service.reset();
        log.info("A database reset has been processed");
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam("account_id") String accountId) throws AccountTransactionException {
        log.info("A balance inquiry request has been received: Account ID to inquiry {}",accountId);
        BigDecimal balance = service.getBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/event")
    public ResponseEntity<EventResponseDTO> postEvent(@Valid @RequestBody EventRequestDTO request) throws AccountTransactionException {
        log.info("A event has been received: {}",request);
        EventResponseDTO response = service.eventHandler(request);
        log.info("Event response: {}",response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
