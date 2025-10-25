package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {
    private UUID id;
    private String tipo;
    private double monto;
    private double saldoAntes;
    private double saldoDespues;
    private String descripcion;
    private LocalDateTime fecha;
    private String referencia;
    private String cuentaRelacionada;
}
