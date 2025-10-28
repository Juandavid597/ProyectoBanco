package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.CuentaAhorros;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CdtDto {

    @NotNull(message = "La cuenta asociada no puede ser nula")
    private CuentaAhorros cuentaAsociada;

    @Positive(message = "El monto invertido debe ser mayor que cero")
    private double montoInvertido;

    @Min(value = 1, message = "El plazo debe ser al menos de 1 mes")
    private int plazoMeses;

    @Positive(message = "La tasa efectiva anual debe ser positiva")
    private double tasaEfectivaAnual;
    
    @NotNull(message = "La fecha de vencimiento es obligatorio")
    @Future(message = "La fecha debe ser futura")
    @Min(value = 90, message = "Deber ser minimo 90 dias")
    @Max(value = 365, message = "Deber ser máximo 90 dias")
    private LocalDate fechaVencimiento;
}
