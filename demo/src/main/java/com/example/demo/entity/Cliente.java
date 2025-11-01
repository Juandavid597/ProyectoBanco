package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate fechaRegistro;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;
    private List<CDT> cdts;
    private TarjetaCredito tarjeta;
    private CuentaAhorros cuenta; 
    private boolean activo;

    public Cliente(String nombre, String documento, String email, String telefono,boolean activo) {

        this.id = UUID.randomUUID();
        this.fechaRegistro = LocalDate.now();
        this.cdts = new ArrayList<>();
        
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.tarjeta = null;
        this.activo = activo; 
        this.cuenta = null; 
    }

    
}
