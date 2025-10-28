package com.example.demo.entity;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    private UUID id;
    private double valorOriginal;
    private int cuotas;
    private double interesMensual;
    private double totalConInteres;
    private double saldoPendiente;
    private LocalDate fechaCompra;
    private String descripcion;
    private String referenciaPago;


    public Compra(double valorOriginal, int cuotas, double interesMensual, double totalConInteres,
            double saldoPendiente, LocalDate fechaCompra, String descripcion, String referenciaPago) {

        this.id = UUID.randomUUID();
        this.fechaCompra = LocalDate.now();
        this.referenciaPago = referenciaPago; 
        this.valorOriginal = valorOriginal;
        this.cuotas = cuotas;
        this.interesMensual = interesMensual;
        this.totalConInteres = totalConInteres;
        this.saldoPendiente = saldoPendiente;
        this.descripcion = descripcion;
        
    }
        //Pendiente verificar que funcione la referencia automatimaticamente
      public  String referenciaPago(){

        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    }

}
