package com.ebanx.accountmanager.controller;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.model.Account;
import com.ebanx.accountmanager.service.AccountManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class AccountManagerController {
    @Autowired
    private AccountManagerService service;
    @PostMapping("/reset")
    public ResponseEntity<String> reset(){
        return ResponseEntity.ok().build();
    }
    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam("account_id") Integer accountId){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/event")
    public ResponseEntity<EventResponseDTO> postEvent(@Valid @RequestBody EventRequestDTO request) throws AccountTransactionException {
        log.info("A event has been ingressed: {}",request);
        EventResponseDTO response = service.eventHandler(request);
        log.info("Event response: {}",response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
