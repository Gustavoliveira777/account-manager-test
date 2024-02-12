package com.ebanx.accountmanager.dto;

import com.ebanx.accountmanager.enumerator.TypeActionEnumerator;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class EventRequestDTO {
    @NotNull
    private TypeActionEnumerator type;
    private String destination;
    private String origin;
    @NotNull
    private BigDecimal amount;

    public EventRequestDTO(String type, String destination, String origin, BigDecimal amount) {
        this.type = TypeActionEnumerator.valueOf(type);
        this.destination = destination;
        this.origin = origin;
        this.amount = amount;
    }
    public EventRequestDTO(TypeActionEnumerator type, String destination, String origin, BigDecimal amount) {
        this.type = type;
        this.destination = destination;
        this.origin = origin;
        this.amount = amount;
    }
}
