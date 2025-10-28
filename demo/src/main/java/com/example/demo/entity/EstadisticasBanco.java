package com.example.demo.entity;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EstadisticasBanco {

    private UUID id;
    private double montoTotalCuentas;
    private double montoTotalInvertidoCDT;
    private double gananciasTotalesCDT;
    private double deudaTotalTarjetas;
    private double promedioSaldoPorCliente;
    private double promedioDeudaPorTarjeta;
    private int totalClientes;
    private int totalCDTsActivos;
    private int totalCuentasActivas;
    private int totalTarjetasActivas;
    private LocalDate fechaActualizacion;


    public EstadisticasBanco(double montoTotalCuentas, double montoTotalInvertidoCDT, double gananciasTotalesCDT,
            double deudaTotalTarjetas, double promedioSaldoPorCliente, double promedioDeudaPorTarjeta,
            int totalClientes, int totalCDTsActivos, int totalCuentasActivas, int totalTarjetasActivas) {

        this.fechaActualizacion = LocalDate.now();
        this.montoTotalCuentas = montoTotalCuentas;
        this.montoTotalInvertidoCDT = montoTotalInvertidoCDT;
        this.gananciasTotalesCDT = gananciasTotalesCDT;
        this.deudaTotalTarjetas = deudaTotalTarjetas;
        this.promedioSaldoPorCliente = promedioSaldoPorCliente;
        this.promedioDeudaPorTarjeta = promedioDeudaPorTarjeta;
        this.totalClientes = totalClientes;
        this.totalCDTsActivos = totalCDTsActivos;
        this.totalCuentasActivas = totalCuentasActivas;
        this.totalTarjetasActivas = totalTarjetasActivas;
        
    }

    
} 
