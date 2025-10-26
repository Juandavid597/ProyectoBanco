package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BancoDto {

    @NotBlank(message = "El nombre es necesario")
    @Size(min = 2, max = 50, message = "El nombre deber tener entre 2 y 50 caracteres")
    private String nombre;

}
