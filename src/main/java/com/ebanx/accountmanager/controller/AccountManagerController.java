package com.ebanx.accountmanager.controller;

import com.ebanx.accountmanager.dto.EventRequestDTO;
import com.ebanx.accountmanager.dto.EventResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountManagerController {
    @PostMapping("/reset")
    public ResponseEntity<String> reset(){
        return ResponseEntity.ok().build();
    }
    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam("account_id") Integer accountId){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/event")
    public ResponseEntity<EventResponseDTO> postEvent(@RequestBody EventRequestDTO request){
        return ResponseEntity.created().build();
    }
}
