package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CuentaDeAhorroDto;
import com.example.demo.dto.MovimientoDto;
import com.example.demo.entity.Banco;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.Movimiento;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Movimiento")
@CrossOrigin("*")
public class MovimientoController {
    private final Banco fakeDb = Banco.getInstancia();


    @PostMapping("/{numeroCuenta}")
    public ResponseEntity<?> depositarDinero(@PathVariable String numeroCuenta, @Valid @RequestBody MovimientoDto MovimientoActualizar, BindingResult result){

        if (result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{

            //Validar si existe la cuenta solicitada por numero de cuenta
            
            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            if (cuentaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta asociada con el número de cuenta ingresada");
            }

            
    
            //Si el cliente no tiene una cuenta procedemos a crear la respectiva cuenta.

            Movimiento newDeposito= new Movimiento(MovimientoActualizar.getTipo(), MovimientoActualizar.getMonto(), cuentaFound.getSaldo(), MovimientoActualizar.getDescripcion(), cuentaFound.getNumeroCuenta());


            newDeposito.setSaldoDespues(newDeposito.getSaldoAntes()-newDeposito.getMonto());

            cuentaFound.getMovimientos().add(newDeposito);

            return ResponseHelper.response(HttpStatus.OK, true, newDeposito, "Se ha hecho el deposito correctamente");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }

}
