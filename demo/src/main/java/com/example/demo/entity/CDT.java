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



    public CDT(CuentaAhorros cuentaAsociada, double montoInvertido, int plazoMeses, double tasaEfectivaAnual,
            LocalDate fechaVencimiento, double gananciaBruta, double retencion,
            double gananciaNeta, boolean activo, String estado) {

        this.id = UUID.randomUUID();   
        this.fechaCreacion = LocalDate.now();   
        this.fechaVencimiento = fechaVencimiento.plusDays(plazoMeses); //revisar si funciona bien
        this.cuentaAsociada = cuentaAsociada;
        this.montoInvertido = montoInvertido;
        this.plazoMeses = plazoMeses;
        this.tasaEfectivaAnual = tasaEfectivaAnual;
        this.gananciaBruta = gananciaBruta;
        this.retencion = retencion;
        this.gananciaNeta = gananciaNeta;
        this.activo = activo;
        this.estado = estado;
    }

    

}
