package com.example.demo.entity;

import java.util.ArrayList;
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
    private List<Cdt> cdts;
    private List<TarjetaCredito> tarjetas;
    private double totalGananciasCDT;
    private double totalDineroEnCuentas;

    // Configuración de tasas de interés para CDT según plazo
    private static final double TASA_3_MESES = 0.05; // 5% EA
    private static final double TASA_6_MESES = 0.055; // 5.5% EA
    private static final double TASA_9_MESES = 0.058; // 5.8% EA
    private static final double TASA_12_MESES = 0.06; // 6% EA
    private static final double TASA_18_MESES = 0.065; // 6.5% EA
    private static final double TASA_24_MESES = 0.07; // 7% EA


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
        this.cuentas = new ArrayList<CuentaAhorros>();
        this.clientes = new ArrayList<Cliente>();
        this.cdts = new ArrayList<Cdt>();
        this.tarjetas = new ArrayList<TarjetaCredito>();

    }

    public double obtenerTasaCdt(String plazoMeses){
        switch (plazoMeses) {
            case "3":
                return TASA_3_MESES;
            case "6":
                return TASA_6_MESES;
            case "9":
                return TASA_9_MESES;
            case "12":
                return TASA_12_MESES;
            case "18":
                return TASA_24_MESES;
            case "24":
                return TASA_24_MESES;
            default:
                return TASA_6_MESES;
        }
    }
}
