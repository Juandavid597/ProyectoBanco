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
public class Cliente {
    private UUID id;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;
    private CuentaAhorros cuenta;
    private List<CDT> cdts;
    private TarjetaCredito tarjeta;
    private LocalDate fechaRegistro;
    private boolean activo;
}
