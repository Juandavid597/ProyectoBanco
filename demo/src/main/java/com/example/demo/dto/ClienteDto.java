package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.demo.entity.CDT;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.TarjetaCredito;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    private CuentaAhorros cuenta;
    private List<CDT> cdts;
    private TarjetaCredito tarjeta;
    private LocalDate fechaRegistro;
    private boolean activo;
}
