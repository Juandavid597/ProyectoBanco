package com.example.demo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimularCdtDto {

    @PositiveOrZero(message = "El monto invertido debe ser mayor que cero")
    private double montoInvertido;

    @Pattern(regexp = "3|6|9|12|24", message="El valor debe de ser 3,6,9,12 o 24")
    private String plazoMeses;
    
}
