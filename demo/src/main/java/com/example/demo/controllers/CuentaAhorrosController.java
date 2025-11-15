package com.example.demo.controllers;



import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CuentaDeAhorroDto;
import com.example.demo.entity.Banco;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.helpers.ResponseHelper;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuenta")
@CrossOrigin("*")

public class CuentaAhorrosController {

    private final Banco fakeDb = Banco.getInstancia();


    @GetMapping("{documento}") //buscar información de cuenta por numero de documento
    public ResponseEntity<?> consultarCuentaDocumento(@PathVariable String documento){

        try{

            Cliente clienteFound = fakeDb.getClientes().stream().filter((item -> item.getDocumento().equals(documento))).findFirst().orElse(null);

            if (clienteFound == null){
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encontro registro de cliente con el Documento");
            }

            if (clienteFound.getCuenta() == null) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "No existe una cuenta de ahorros asociada al Documento ingresado");
            }

            return ResponseHelper.response(HttpStatus.OK, true, clienteFound.getCuenta(), "Información de cuenta de ahorros encontrada");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }


    @PostMapping("/{documento}")
    public ResponseEntity<?> crearCuenta(@PathVariable String documento,@Valid @RequestBody CuentaDeAhorroDto cuentaAhorros, BindingResult result){

        if (result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{

            //Validar si existe el cliente solicitado por documento
            
            Cliente clienteFound = fakeDb.getClientes().stream().filter(item->item.getDocumento().equals(documento)).findFirst().orElse(null);

            if (clienteFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentran clientes con el documento ingresado");
            }

            //Si si existe el cliente, vamos a validar si tiene una cuenta

            if (clienteFound.getCuenta() != null) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "El cliente el cual has buscado ya tiene una cuenta creada");
            }
    
            //Si el cliente no tiene una cuenta procedemos a crear la respectiva cuenta.

            CuentaAhorros newCuenta= new CuentaAhorros(clienteFound.getNombre(), clienteFound.getDocumento(), cuentaAhorros.getSaldo(), cuentaAhorros.getTipoCuenta(), true);

            clienteFound.setCuenta(newCuenta);

            fakeDb.getCuentas().add(newCuenta);

            return ResponseHelper.response(HttpStatus.OK, true, newCuenta, "La cuenta se creo exitosamente en el banco");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }


    @DeleteMapping("/{documento}")
    public ResponseEntity<?> eliminarCliente(@PathVariable UUID id){

        try{

            Cliente clienteFound = fakeDb.getClientes().stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);

            if(clienteFound == null){
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "Cliente no encontrado");
            }

            if (clienteFound.getCuenta() == null) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "No existe una cuenta de ahorros asociada al Documento ingresado");
            }

            fakeDb.getCuentas().remove(clienteFound.getCuenta());
            return ResponseHelper.response(HttpStatus.OK, true, clienteFound, "Cuenta de ahorros eliminada correctamente");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }
    
    
}
