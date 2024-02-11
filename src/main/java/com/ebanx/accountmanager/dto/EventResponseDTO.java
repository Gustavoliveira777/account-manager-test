package com.ebanx.accountmanager.dto;

import com.ebanx.accountmanager.model.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponseDTO{
    private Account destination;
}
