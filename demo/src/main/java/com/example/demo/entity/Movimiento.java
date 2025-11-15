package com.example.demo.entity;

import java.time.LocalDate;

import java.util.UUID;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class Movimiento {
    private UUID id;
    private String tipo;
    private double monto;
    private double saldoAntes;
    private double saldoDespues;
    private String descripcion;
    private LocalDate fecha;
    private String referencia;
    private String cuentaRelacionada;
    private String cuentaOrigen;
    private String cuentaDestino;


    public Movimiento(String tipo, double monto, double saldoAntes, String descripcion, String cuentaRelacionada) {

        this.id = UUID.randomUUID();      
        this.tipo = tipo;
        this.monto = monto;
        this.saldoAntes = saldoAntes;
        this.saldoDespues = 0;
        this.descripcion = descripcion;
        this.fecha = LocalDate.now();
        this.referencia = "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.cuentaRelacionada = cuentaRelacionada;


    }


     
    
}
