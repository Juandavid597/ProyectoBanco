package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MovimientoDto;
import com.example.demo.entity.Banco;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.Movimiento;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/Transacción")
@CrossOrigin("*")
public class TransaccionController {

    private final Banco fakeDb = Banco.getInstancia();


    


    // @PostMapping("/transferencia/{numeroCuenta}")
    // public ResponseEntity<?> transferirDinero(@PathVariable String numeroCuenta, @Valid @RequestBody MovimientoDto MovimientoActualizar, BindingResult result){
        

    //     if (result.hasErrors()){
    //         return ResponseHelper.validFields(result);
    //     }

    //     try{

    //         //Validar si existe la cuenta solicitada por numero de cuenta
            
    //         CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

    //         if (cuentaFound == null) {
    //             return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta asociada con el número de cuenta ingresada");
    //         }



    //         if (MovimientoActualizar.getMonto()>cuentaFound.getSaldo()) {
    //             return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "El monto que usted desea retirar supera el valor disponible en su cuenta bancaria.");
    //         }
            
    
    //         //Si el cliente no tiene una cuenta procedemos a crear la respectiva cuenta.

    //         // Movimiento newRetiro= new Movimiento(MovimientoActualizar.getTipo(), MovimientoActualizar.getMonto(), cuentaFound.getSaldo(), MovimientoActualizar.getDescripcion(), cuentaFound.getNumeroCuenta(), cuentaFound.getNumeroCuenta(), MovimientoActualizar.getCuentaDestino());


    //         // newRetiro.setSaldoDespues(newRetiro.getSaldoAntes()-newRetiro.getMonto());

    //         // cuentaFound.getMovimientos().add(newRetiro);

    //         // return ResponseHelper.response(HttpStatus.OK, true, newRetiro, "Se ha hecho el Retiro correctamente");

    //     }

    //     catch (Exception e){
    //         return ResponseHelper.catchResponse(e);
    //     }
    // }
    
}
