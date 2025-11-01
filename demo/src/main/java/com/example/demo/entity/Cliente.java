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
       
 // ESTABA EN CUENTA DE AHORROS
        

        // datos solicitados en el DTO
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo; // esta variable la agregue por flujo, es necesario crearla acá o sólo en el DTO? debe estar en la lista de clientes?
        
        this.activo = activo; // cómo se usa esta varaiable, al hacer pruebas en POSTMAN dice que esta inactivo el cliente
        
        
    }


    
}
