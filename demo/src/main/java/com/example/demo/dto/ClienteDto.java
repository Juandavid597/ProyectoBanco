package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.TarjetaCredito;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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

// Nombre no vacío = OK
// Documento único (no puede haber dos clientes con el mismo documento) = OK
// Saldo inicial ≥ $10.000 = OK
// Documento solo números = OK
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre deber tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    @Pattern(regexp = "\\d+", message = "El documento sólo puede contener números")
    @Size(min = 2, max = 12, message = "El documento deber tener entre 2 a 12 numeros")
    private String documento;
    
    @Email(message = "El correo no es valido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\s]{7,20}$", message = "El número de teléfono no tiene un formato válido")
    @Pattern(regexp = "\\d{1,10}", message = "El número de teléfono debe contener entre 9 y 15 dígitos")
    private String telefono;


    @NotNull(message = "El saldo inicial es obligatorio")
    @Min(value = 10000, message = "El saldo minimo son $10.000")
    private double saldo;

  

}
