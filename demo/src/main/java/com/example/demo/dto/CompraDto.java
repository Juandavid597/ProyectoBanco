package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDto {
    @Positive(message = "El valor original debe ser mayor que cero")
    private double valorOriginal;

    @Min(value = 1, message = "El número de cuotas debe ser al menos 1")
    private int cuotas;

    @Positive(message = "El interés mensual debe ser positivo")
    private double interesMensual;

    @Positive(message = "El total con interés debe ser mayor que cero")
    private double totalConInteres;

    @Min(value = 0, message = "El saldo pendiente no puede ser negativo")
    private double saldoPendiente;

    @NotNull(message = "La fecha de compra es obligatoria")
    @PastOrPresent(message = "La fecha de compra no puede ser futura")
    private LocalDate fechaCompra;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "La referencia de pago es obligatoria")
    private String referenciaPago;
}
