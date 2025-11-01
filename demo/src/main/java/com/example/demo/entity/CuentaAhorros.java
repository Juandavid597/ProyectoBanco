package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaAhorros {
    
    private UUID id;
    private Cliente propietario;
    private double saldo;
    private List<Movimiento> movimientos;
    private LocalDate fechaCreacion;
    private boolean activa;
    private String tipoCuenta;
    private String numeroCuenta;



    public CuentaAhorros(String numeroCuenta, Cliente propietario, double saldo, LocalDate fechaCreacion,
            boolean activa, String tipoCuenta) {

        this.id = UUID.randomUUID();
        this.fechaCreacion = LocalDate.now();
        this.numeroCuenta = "ACC-" + id.toString().replaceAll("[^\\d]", "").substring(0, 10);
        this.movimientos = new ArrayList<>();
        
        this.propietario = propietario;
        this.saldo = saldo;
        this.activa = activa;
        this.tipoCuenta = tipoCuenta;
    }

    
}