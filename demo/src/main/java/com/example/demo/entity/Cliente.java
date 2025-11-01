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
    private LocalDate fechaRegistro;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;
    private double saldo;
    private List<CDT> cdts;
    private TarjetaCredito tarjeta; //cómo se usa está variable, viene de una lista o la clase de tarjeta?
    private boolean activo;

    // private CuentaAhorros cuenta; //esto no es lo mismo a numero de cuenta que es el que tambien se solicito crear en cuenta de ahorros?
    


    public Cliente(String nombre, String documento, String email, String telefono, double saldo) {

        this.id = UUID.randomUUID();
        this.fechaRegistro = LocalDate.now();
        

        // datos solicitados en el DTO
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        
        
        this.activo = activo; // cómo se usa esta varaiable, al hacer pruebas en POSTMAN dice que esta inactivo el cliente
        
        
    }

// CuentaAhorros cuenta - La cuenta del cliente (solo UNA)
// List<CDT> cdts - Lista de CDTs del cliente
// TarjetaCredito tarjeta - Tarjeta de crédito (puede ser null)



    
}
