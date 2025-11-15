package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




// Cliente titular - Cliente dueño de la tarjeta
// double cupoTotal - Cupo total asignado
// double cupoDisponible - Cupo disponible actual
// double deudaActual - Deuda total pendiente
// List<Compra> compras - Lista de compras realizadas

// boolean activa - Estado de la tarjeta

@Data
@AllArgsConstructor
@NoArgsConstructor

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


    public TarjetaCredito(String numeroTarjeta, Cliente titular, double cupoTotal, double cupoDisponible, double deudaActual, LocalDate fechaEmision, LocalDate fechaVencimiento, double pagoMinimoPorcentaje, boolean activa) {

        // UUID id - Identificador único
        this.id = UUID.randomUUID();
        // LocalDate fechaEmision - Fecha de emisión
        this.fechaEmision = LocalDate.now();  
        this.fechaVencimiento = fechaVencimiento.plusYears(4); 
        this.numeroTarjeta   = generarNumeroTarjeta();

        this.titular = titular;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.deudaActual = deudaActual;
        this.pagoMinimoPorcentaje = pagoMinimoPorcentaje;
        this.activa = activa;
    }

    // String numeroTarjeta - Número de tarjeta (formato: 4532-XXXX-XXXX-XXXX)

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
