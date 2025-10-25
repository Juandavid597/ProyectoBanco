package com.example.demo.entity;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
    private UUID id;
    private double valorOriginal;
    private int cuotas;
    private double interesMensual;
    private double totalConInteres;
    private double saldoPendiente;
    private LocalDate fechaCompra;
    private String descripcion;
    private String referenciaPago;
}
