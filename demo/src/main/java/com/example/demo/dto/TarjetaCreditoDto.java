package com.example.demo.dto;

import com.example.demo.entity.Cliente;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaCreditoDto {



  
@NotNull(message = "El pago minimo es obligatorio")
    private double pagoMinimoPorcentaje;


   // Solicitar cupo deseado (mínimo $500.000, máximo $5.000.000) = OK

@NotNull(message = "el cupo total es obligatorio")
@Min(value= 500000, message = "el cupo minimo es de $500.000")
@Max(value = 5000000, message = "el cupo maximo es de $5.000.000")
    private double cupoTotal;

// double pagoMinimoPorcentaje - Porcentaje de pago mínimo (5%)
@Min(value = 5, message = "El porcentaje debe ser 5%")
@Max(value = 5, message = "El porcentaje debe ser 5%")
private int porcentajePagoMinimo; 



  
    
}

