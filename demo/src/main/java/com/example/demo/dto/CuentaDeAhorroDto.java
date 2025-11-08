package com.example.demo.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDeAhorroDto {

    // Saldo inicial ≥ $10.000 = OK

    
    @NotNull(message = "El saldo inicial es obligatorio")
    @Min(value = 10000, message = "El saldo debe ser mayor a $10.000")
    @PositiveOrZero(message = "El monto no puede ser negativo")
    private double saldo;


    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(regexp = "ahorro|corriente", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El tipo de cuenta debe ser 'ahorro' o 'corriente'")
    private String tipoCuenta;
}
