package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimularCdtDto{

    @PositiveOrZero(message = "El monto invertido debe ser mayor que cero")
    private double montoInvertido;

    // @Min(value = 3, message = "El plazo mínimo permitido es 3 meses")
    // @Max(value = 24, message = "El plazo máximo permitido es 24 meses")
    private int plazoMeses;

     @AssertTrue(message = "El plazo debe ser 3, 6, 9, 12, 18 o 24 meses")
    public boolean isPlazoMesesValido() {
        return plazoMeses == 3 ||
               plazoMeses == 6 ||
               plazoMeses == 9 ||
               plazoMeses == 12 ||
               plazoMeses == 18 ||
               plazoMeses == 24;
    }
  
}
