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


    public Movimiento(String tipo, double monto, double saldoAntes, double saldoDespues, String descripcion,
            LocalDateTime fecha, String referencia, String cuentaRelacionada) {

        this.id = UUID.randomUUID();      
        this.tipo = tipo;
        this.monto = monto;
        this.saldoAntes = saldoAntes;
        this.saldoDespues = saldoDespues;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.referencia = referencia;
        this.cuentaRelacionada = cuentaRelacionada;
    }


     
        //Pendiente verificar que funcione la referencia automatimaticamente
      public  String referenciaPago(){

        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    }
    
}
