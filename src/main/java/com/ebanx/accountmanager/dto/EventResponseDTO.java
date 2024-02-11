package com.ebanx.accountmanager.dto;

import com.ebanx.accountmanager.model.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventResponseDTO{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Account origin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Account destination;
}
