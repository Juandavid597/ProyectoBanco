package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.Cliente;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDeAhorroDto {

    // Saldo inicial ≥ $10.000 = OK

    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotNull(message = "El propietario no puede ser nulo")
    private Cliente propietario;

    @Min(value = 0, message = "El saldo no puede ser negativo")
    private double saldo;

    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha de creación no puede ser futura")
    private LocalDate fechaCreacion;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipoCuenta;
}
