package com.example.demo.entity;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDT {
    private UUID id;
    private CuentaAhorros cuentaAsociada;
    private double montoInvertido;
    private int plazoMeses;
    private double tasaEfectivaAnual;
    private LocalDate fechaCreacion;
    private LocalDate fechaVencimiento;
    private double gananciaBruta;
    private double retencion;
    private double gananciaNeta;
    private boolean activo;
    private String estado;
}
