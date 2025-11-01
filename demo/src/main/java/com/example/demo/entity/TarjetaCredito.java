package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TarjetaCredito {
    private UUID id;
    private String numeroTarjeta;
    private Cliente titular;
    private double cupoTotal;
    private double cupoDisponible;
    private double deudaActual;
    private List<Compra> compras;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private double pagoMinimoPorcentaje;
    private boolean activa;


    public TarjetaCredito(String numeroTarjeta, Cliente titular, double cupoTotal, double cupoDisponible,
            double deudaActual, LocalDate fechaEmision, LocalDate fechaVencimiento, double pagoMinimoPorcentaje,
            boolean activa) {


        this.id = UUID.randomUUID();
        this.fechaEmision = LocalDate.now();  
        this.fechaVencimiento = fechaVencimiento.plusYears(3); 
        this.numeroTarjeta = "TDC-" + id.toString().replaceAll("[^\\d]", "").substring(0, 10);
        this.titular = titular;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.deudaActual = deudaActual;
        this.pagoMinimoPorcentaje = pagoMinimoPorcentaje;
        this.activa = activa;
    }

    
}
