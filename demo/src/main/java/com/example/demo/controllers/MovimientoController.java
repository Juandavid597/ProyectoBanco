package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MovimientoDto;
import com.example.demo.entity.Banco;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.Movimiento;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Movimiento")
@CrossOrigin("*")
public class MovimientoController {
    private final Banco fakeDb = Banco.getInstancia();


    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> listarMovimientos(@PathVariable String  numeroCuenta){


        try{

            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            return ResponseHelper.response(HttpStatus.OK, true, cuentaFound.getMovimientos(), "Listado de todos los Movimientos de la cuenta bancaria buscada.");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }


    @GetMapping("{numeroCuenta}/{referencia}") //buscar información de cuenta por numero de documento
    public ResponseEntity<?> buscarMovimientoReferencia(@PathVariable String  numeroCuenta, @PathVariable String referencia){

        try{

            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            if (cuentaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta asociada con el número de cuenta ingresada");
            }

            Movimiento movimientoFound = cuentaFound.getMovimientos().stream().filter((item -> item.getReferencia().equals(referencia))).findFirst().orElse(null);


            return ResponseHelper.response(HttpStatus.OK, true, movimientoFound, "Movimiento encontrado por referencia");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }



    //método para depositar dinero

    @PostMapping("/deposito/{numeroCuenta}")
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


            newDeposito.setSaldoDespues(newDeposito.getSaldoAntes()+newDeposito.getMonto());
            cuentaFound.setSaldo(newDeposito.getSaldoDespues());

            cuentaFound.getMovimientos().add(newDeposito);

            return ResponseHelper.response(HttpStatus.OK, true, newDeposito, "Se ha hecho el deposito correctamente");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }



    //Metodo para retirar dinero

    @PostMapping("/retiro/{numeroCuenta}")
    public ResponseEntity<?> retirarDinero(@PathVariable String numeroCuenta, @Valid @RequestBody MovimientoDto MovimientoActualizar, BindingResult result){
        

        if (result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{

            //Validar si existe la cuenta solicitada por numero de cuenta
            
            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            if (cuentaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta asociada con el número de cuenta ingresada");
            }



            if (MovimientoActualizar.getMonto()>cuentaFound.getSaldo()) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "El monto que usted desea retirar supera el valor disponible en su cuenta bancaria.");
            }
            
    
            //Si el cliente no tiene una cuenta procedemos a crear la respectiva cuenta.

            Movimiento newRetiro= new Movimiento(MovimientoActualizar.getTipo(), MovimientoActualizar.getMonto(), cuentaFound.getSaldo(), MovimientoActualizar.getDescripcion(), cuentaFound.getNumeroCuenta());


            newRetiro.setSaldoDespues(newRetiro.getSaldoAntes()-newRetiro.getMonto());
            cuentaFound.setSaldo(newRetiro.getSaldoDespues());

            cuentaFound.getMovimientos().add(newRetiro);

            return ResponseHelper.response(HttpStatus.OK, true, newRetiro, "Se ha hecho el Retiro correctamente");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }

    //Método Para eliminar

    @DeleteMapping("{numeroCuenta}/{referencia}") //buscar información de cuenta por numero de documento
    public ResponseEntity<?> eliminarMovimientoReferencia(@PathVariable String  numeroCuenta, @PathVariable String referencia){

        try{

            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            if (cuentaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta asociada con el número de cuenta ingresada");
            }

            Movimiento movimientoFound = cuentaFound.getMovimientos().stream().filter((item -> item.getReferencia().equals(referencia))).findFirst().orElse(null);

            cuentaFound.getMovimientos().remove(movimientoFound);


            return ResponseHelper.response(HttpStatus.OK, true, movimientoFound, "Movimiento encontrado por referencia");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }

    






}
