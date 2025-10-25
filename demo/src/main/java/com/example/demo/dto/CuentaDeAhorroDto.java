package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Movimiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDeAhorroDto {
    private UUID id;
    private String numeroCuenta;
    private Cliente propietario;
    private double saldo;
    private List<Movimiento> movimientos;
    private LocalDate fechaCreacion;
    private boolean activa;
    private String tipoCuenta;
}
