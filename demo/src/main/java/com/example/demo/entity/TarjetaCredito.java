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


    //     UUID id - Identificador único
// String numeroTarjeta - Número de tarjeta (formato: 4532-XXXX-XXXX-XXXX)
// Cliente titular - Cliente dueño de la tarjeta
// double cupoTotal - Cupo total asignado
// double cupoDisponible - Cupo disponible actual
// double deudaActual - Deuda total pendiente
// List<Compra> compras - Lista de compras realizadas
// LocalDate fechaEmision - Fecha de emisión
// LocalDate fechaVencimiento - Fecha de vencimiento (4 años después)
// double pagoMinimoPorcentaje - Porcentaje de pago mínimo (5%)
// boolean activa - Estado de la tarjeta

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
        // this.numeroTarjeta = "TDC-" + id.toString().replaceAll("[^\\d]", "").substring(0, 10);
        this.numeroTarjeta = generarNumeroTarjeta();



        this.titular = titular;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.deudaActual = deudaActual;
        this.pagoMinimoPorcentaje = pagoMinimoPorcentaje;
        this.activa = activa;
    }


    private String generarNumeroTarjeta() {
        // Genera un UUID y extrae solo los dígitos
        String uuidNumerico = UUID.randomUUID().toString().replaceAll("[^\\d]", "");

        // Asegura que tenga al menos 12 dígitos
        while (uuidNumerico.length() < 12) {
            uuidNumerico += (int)(Math.random() * 10); // rellena con dígitos aleatorios
        }

        // Divide en bloques de 4
        String bloque1 = uuidNumerico.substring(0, 4);
        String bloque2 = uuidNumerico.substring(4, 8);
        String bloque3 = uuidNumerico.substring(8, 12);

        return String.format("4532-%s-%s-%s", bloque1, bloque2, bloque3);

        
    }







}
