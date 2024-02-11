package com.ebanx.accountmanager.dto;

import com.ebanx.accountmanager.enumerator.TypeActionEnumerator;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDTO {
    @NotNull
    private TypeActionEnumerator type;
    private Integer destination;
    private Integer origin;
    @NotNull
    private Double amount;

}
