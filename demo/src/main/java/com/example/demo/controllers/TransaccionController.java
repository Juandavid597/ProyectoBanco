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


import com.example.demo.dto.TransaccionDto;
import com.example.demo.entity.Banco;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.Transaccion;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/Transacción")
@CrossOrigin("*")
public class TransaccionController {

    private final Banco fakeDb = Banco.getInstancia();


    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> listarMovimientos(@PathVariable String  numeroCuenta){


        try{

            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            return ResponseHelper.response(HttpStatus.OK, true, cuentaFound.getTransaccionesCliente(), "Listado de todas las Transacciones de la cuenta bancaria buscada.");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }


    @GetMapping("{numeroCuenta}/{referenciaTransaccion}") //buscar información de cuenta por numero de documento
    public ResponseEntity<?> buscarMovimientoReferencia(@PathVariable String  numeroCuenta, @PathVariable String referenciatransaccion){

        try{

            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            if (cuentaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta asociada con el número de cuenta ingresada");
            }

            Transaccion transaccionFound = cuentaFound.getTransaccionesCliente().stream().filter((item -> item.getReferenciaTransaccion().equals(referenciatransaccion))).findFirst().orElse(null);


            return ResponseHelper.response(HttpStatus.OK, true, transaccionFound, "Movimiento encontrado por referencia");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }


    @PostMapping("/transferencia/{numeroCuenta}")
    public ResponseEntity<?> transferirDinero(@PathVariable String numeroCuenta, @Valid @RequestBody TransaccionDto transaccionActualizar, BindingResult result){
        

        if (result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{

            //Buscar cuenta origen
            
            CuentaAhorros cuentaFound = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);

            //Validar si existe la cuenta origen

            if (cuentaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta origen asociada con el número de cuenta ingresada");
            }



            if (transaccionActualizar.getMonto()>cuentaFound.getSaldo()) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "El monto que usted desea transferir supera el valor disponible en su cuenta bancaria.");
            }
            
    
            //Buscar cuenta destino

            CuentaAhorros cuenta2Found = fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(transaccionActualizar.getCuentaDestino())).findFirst().orElse(null);

            //validar si existe la otra cuenta

            if (cuenta2Found == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentra una cuenta Destino asociada con el número de cuenta ingresada");
            }

            if (cuentaFound.getNumeroCuenta().equals(cuenta2Found.getNumeroCuenta())) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "Las cuentas ingresadas son iguales, por favor ingresar los valores correctos");
            }

            Transaccion newTransaccion = new Transaccion(cuentaFound.getNumeroCuenta(), cuenta2Found.getNumeroCuenta(), transaccionActualizar.getMonto(), cuentaFound.getSaldo(), cuenta2Found.getSaldo());

            newTransaccion.setDescripcion("Transferencia de una cuenta bancaria a otra.");

            newTransaccion.setSaldoDespuesOrigen(newTransaccion.getSaldoAntesOrigen() - transaccionActualizar.getMonto());
            cuentaFound.setSaldo(newTransaccion.getSaldoDespuesOrigen());

            newTransaccion.setSaldoDespuesDestino(newTransaccion.getSaldoAntesDestino() + transaccionActualizar.getMonto());
            cuenta2Found.setSaldo(newTransaccion.getSaldoDespuesDestino());

            fakeDb.getTransacciones().add(newTransaccion);

            cuentaFound.getTransaccionesCliente().add(newTransaccion);

            return ResponseHelper.response(HttpStatus.OK, true, newTransaccion, "Se ha hecho la transferencia correctamente");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }
    
}
