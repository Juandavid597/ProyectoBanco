package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasBancoDto {

    @Min(value = 0, message = "El monto total de cuentas no puede ser negativo")
    private double montoTotalCuentas;

    @Min(value = 0, message = "El monto total invertido en CDT no puede ser negativo")
    private double montoTotalInvertidoCDT;

    @Min(value = 0, message = "Las ganancias totales del CDT no pueden ser negativas")
    private double gananciasTotalesCDT;

    @Min(value = 0, message = "La deuda total de tarjetas no puede ser negativa")
    private double deudaTotalTarjetas;

    @Min(value = 0, message = "El promedio de saldo por cliente no puede ser negativo")
    private double promedioSaldoPorCliente;

    @Min(value = 0, message = "El promedio de deuda por tarjeta no puede ser negativo")
    private double promedioDeudaPorTarjeta;

    @Min(value = 0, message = "El total de clientes no puede ser negativo")
    private int totalClientes;

    @Min(value = 0, message = "El total de CDTs activos no puede ser negativo")
    private int totalCDTsActivos;

    @Min(value = 0, message = "El total de cuentas activas no puede ser negativo")
    private int totalCuentasActivas;

    @Min(value = 0, message = "El total de tarjetas activas no puede ser negativo")
    private int totalTarjetasActivas;

    @NotNull(message = "La fecha de actualización es obligatoria")
    @PastOrPresent(message = "La fecha de actualización no puede ser futura")
    private LocalDate fechaActualizacion;
}
