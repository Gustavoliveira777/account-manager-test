package com.ebanx.accountmanager.controller;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import com.ebanx.accountmanager.exception.AccountTransactionException;
import com.ebanx.accountmanager.model.Account;
import com.ebanx.accountmanager.service.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
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
        EventResponseDTO response = service.eventHandler(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
