package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuentaAhorros {
    
    private UUID id;
    private String nombrePropietario;
    private String documentoPropietario;
    private double saldo;
    private List<Movimiento> movimientos;
    private LocalDate fechaCreacion;
    private boolean activa;
    private String tipoCuenta;
    private String numeroCuenta;



    public CuentaAhorros(String nombrePropietario, String documentoPropietario, double saldo,String tipoCuenta, boolean activa) {

        this.id = UUID.randomUUID();
        this.fechaCreacion = LocalDate.now();
        this.numeroCuenta = "ACC-" + id.toString().replaceAll("[^\\d]", "").substring(0, 10);
        this.movimientos = new ArrayList<>();
        
        this.nombrePropietario = nombrePropietario;
        this.documentoPropietario = documentoPropietario;
        this.saldo = saldo;
        this.activa = activa;
        this.tipoCuenta = tipoCuenta;
    }

    
}