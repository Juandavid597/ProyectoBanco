package com.example.demo.entity;

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
    private double saldoAntesOrigen;
    private double saldoDespuesOrigen;
    private double saldoAntesDestino;
    private double saldoDespuesDestino;
    private String referenciaTransaccion;


    public Transaccion(String cuentaOrigen, String cuentaDestino, double monto, double saldoAntesOrigen, double saldoAntesDestino) {

        this.id = UUID.randomUUID();
        this.fecha = LocalDateTime.now();  
        this.tipo = "transferencia";
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.estado = null;
        this.descripcion = null;
        this.saldoAntesOrigen = saldoAntesOrigen;
        this.saldoAntesDestino = saldoAntesDestino;
        this.saldoDespuesOrigen = 0;
        this.saldoDespuesDestino = 0;
        this.referenciaTransaccion = "TRANS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();


    }

    

}
