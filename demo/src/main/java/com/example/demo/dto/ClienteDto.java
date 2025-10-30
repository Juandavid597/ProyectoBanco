package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.TarjetaCredito;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    
    @NotBlank(message = "El nombre es necesario")
    @Size(min = 2, max = 50, message = "El nombre deber tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El nombre es necesario")
    @Size(min = 2, max = 50, message = "El nombre deber tener entre 2 y 50 caracteres")
    private String documento;
    
    @Email(message = "El correo no es valido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\s]{7,20}$", message = "El número de teléfono no tiene un formato válido")
    @Pattern(regexp = "\\d{9,15}", message = "El número de teléfono debe contener entre 9 y 15 dígitos")
    private String telefono;

    // validar si estos datos se solicitan al cliente teniendo en cuenta que vienen de otra entidad, para mi este DTO es para 
    // los datos iniciales de registro del seller
    @NotNull(message = "La cuenta de ahorros no puede ser nula")
    private CuentaAhorros cuenta;

    @NotNull(message = "La tarjeta de credido no puede ser nula")
    private TarjetaCredito tarjeta;

}
