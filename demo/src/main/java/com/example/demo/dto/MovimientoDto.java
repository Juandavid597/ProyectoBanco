package com.example.demo.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto {
    
    @NotBlank(message = "El tipo de movimiento es obligatorio (por ejemplo, 'Deposito' o 'Retiro')")
    @Pattern(regexp = "deposito|retiro", flags = Pattern.Flag.CASE_INSENSITIVE, message = "El tipo de cuenta debe ser 'ahorro' o 'corriente'")
    private String tipo;

    @Positive(message = "El monto del movimiento debe ser mayor que cero")
    @NotNull(message = "El monto no debe de estar vacio")
    private double monto;

    @NotBlank(message = "La descripción del movimiento es obligatoria.")
    private String descripcion;


}
