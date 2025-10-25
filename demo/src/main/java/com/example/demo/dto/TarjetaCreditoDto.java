package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Compra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaCreditoDto {
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
}
