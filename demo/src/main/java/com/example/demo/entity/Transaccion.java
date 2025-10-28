package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transaccion {
    private UUID id;
    private String tipo;
    private String cuentaOrigen;
    private String cuentaDestino;
    private double monto;
    private LocalDateTime fecha;
    private String estado;
    private String descripcion;


    public Transaccion(String tipo, String cuentaOrigen, String cuentaDestino, double monto, LocalDateTime fecha,
            String estado, String descripcion) {

        this.id = UUID.randomUUID();
        this.fecha = LocalDateTime.now();  
        this.tipo = tipo;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    

}
