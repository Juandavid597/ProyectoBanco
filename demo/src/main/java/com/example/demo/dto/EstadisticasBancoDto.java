package com.example.demo.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasBancoDto {
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
}
