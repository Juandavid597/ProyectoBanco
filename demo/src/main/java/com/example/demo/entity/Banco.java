package com.example.demo.entity;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
    private UUID id;
    private String nombre;
    private List<Cliente> clientes;
    private List<CuentaAhorros> cuentas;
    private List<CDT> cdts;
    private List<TarjetaCredito> tarjetas;
    private double totalGananciasCDT;
    private double totalDineroEnCuentas;



    public Banco(String nombre, double totalGananciasCDT, double totalDineroEnCuentas) {
        
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.totalGananciasCDT = totalGananciasCDT;
        this.totalDineroEnCuentas = totalDineroEnCuentas;
    }


    

}
