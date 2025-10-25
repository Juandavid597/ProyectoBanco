package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDto {
    private UUID id;
    private String tipo;
    private String cuentaOrigen;
    private String cuentaDestino;
    private double monto;
    private LocalDateTime fecha;
    private String estado;
    private String descripcion;
}
