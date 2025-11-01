package com.example.demo.entity;

import java.util.List;
import java.util.UUID;


import lombok.Data;


@Data
public class Banco {

    private static Banco instancia;

    private UUID id;
    private String nombre = "Banco Real de Antioquia";
    private List<Cliente> clientes;
    private List<CuentaAhorros> cuentas;
    private List<CDT> cdts;
    private List<TarjetaCredito> tarjetas;
    private double totalGananciasCDT;
    private double totalDineroEnCuentas;


    private Banco(){

    }

    public static Banco getInstancia(){
        if (instancia == null) {
            instancia = new Banco(null, 0, 0);
        }

        return instancia;
    }


    private Banco(String nombre, double totalGananciasCDT, double totalDineroEnCuentas) {
        
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.totalGananciasCDT = totalGananciasCDT;
        this.totalDineroEnCuentas = totalDineroEnCuentas;
    }


    

}
