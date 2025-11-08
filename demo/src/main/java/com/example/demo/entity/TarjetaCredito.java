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




// Cliente titular - Cliente dueño de la tarjeta
// double cupoTotal - Cupo total asignado
// double cupoDisponible - Cupo disponible actual
// double deudaActual - Deuda total pendiente
// List<Compra> compras - Lista de compras realizadas
// LocalDate fechaEmision - Fecha de emisión
// boolean activa - Estado de la tarjeta
// Métodos sugeridos:
// realizarCompra(), pagar(), consultarEstado()
// calcularPagoMinimo(), verificarCupo(), actualizarCupo()


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
        this.fechaEmision = LocalDate.now();  

        // Asignar fecha de emisión y vencimiento (4 años)
        // LocalDate fechaVencimiento - Fecha de vencimiento (4 años después)
        this.fechaVencimiento = fechaVencimiento.plusYears(4); 
        
        this.titular = titular;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.deudaActual = deudaActual;
        this.pagoMinimoPorcentaje = pagoMinimoPorcentaje;
        this.activa = activa;
    }

    
        // Generar número de tarjeta único
        // String numeroTarjeta - Número de tarjeta (formato: 4532-XXXX-XXXX-XXXX)

}
