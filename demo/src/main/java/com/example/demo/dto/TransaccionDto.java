package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDto {

    @NotBlank(message = "La cuenta origen es obligatoria.")
    private String cuentaOrigen;

    @NotBlank(message = "La cuenta destino es obligatoria.")
    private String cuentaDestino;

    @Positive(message = "El monto del movimiento debe ser mayor que cero")
    @NotNull(message = "El monto no debe de estar vacio")
    private double monto;

}
