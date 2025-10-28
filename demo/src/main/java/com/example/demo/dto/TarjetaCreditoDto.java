package com.example.demo.dto;

import com.example.demo.entity.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaCreditoDto {
    @NotBlank(message = "El número de tarjeta es obligatorio")
    private String numeroTarjeta;

    @NotNull(message = "El titular de la tarjeta no puede ser nulo")
    private Cliente titular;

    private double pagoMinimoPorcentaje; // validar si entra en el DTO
}
