package com.example.demo.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto {
    
    @NotBlank(message = "El tipo de movimiento es obligatorio (por ejemplo, 'Depósito' o 'Retiro')")
    private String tipo;

    @Positive(message = "El monto del movimiento debe ser mayor que cero")
    @NotNull(message = "El monto no debe de estar vacio")
    private double monto;

    @Min(value = 0, message = "El saldo antes del movimiento no puede ser negativo")
    private double saldoAntes;

    @Min(value = 0, message = "El saldo después del movimiento no puede ser negativo")
    private double saldoDespues;

    private String descripcion;

    @NotBlank(message = "La cuenta relacionada es obligatoria")
    private String cuentaRelacionada;
}
