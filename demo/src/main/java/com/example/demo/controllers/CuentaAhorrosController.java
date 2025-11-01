package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDto;
import com.example.demo.entity.Banco;
import com.example.demo.entity.Cliente;
import com.example.demo.helpers.ResponseHelper;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuenta")
@CrossOrigin("*")

public class CuentaAhorrosController {



    // @RestController
    // @RequestMapping(/)

    @GetMapping
    public ResponseEntity<?> listarClientes(){


        try{
            return ResponseHelper.response(HttpStatus.OK, true, Banco.getInstancia().getClientes(), "Listado de todos los clientes creados en el banco");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }

    @GetMapping("{id}") //buscar información de cuenta por numero de documento
    public ResponseEntity<?> ListarClientesDocumento(@PathVariable UUID id){

        try{

            Cliente clientesFound = Banco.getInstancia().getClientes().stream().filter((item -> item.getId().equals(id))).findFirst().orElse(null);

            if (clientesFound == null){
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encontro registro de cliente con el Id");
            }

            return ResponseHelper.response(HttpStatus.OK, true, clientesFound, "Cliente encontrado con ID");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }


    @PostMapping
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDto cliente, BindingResult result){
// Validar que el documento no esté registrado
// Solicitar saldo inicial (mínimo $10.000) = OK
// Generar número de cuenta único (puede usar UUID o formato numérico) = OK -> se segiere en la clase cuenta de ahorros, pero para el metodo lo solicita el flujo, revisar con PROFESOR
// Asignar fecha de creación = OK
// Guardar cliente y cuenta en las estructuras de datos = se guarda correctamente en lista clientes. Se debe guardar cuenta en lista cuenta ahorros?


        if (result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{

            //Validar numero de docuemnto sea unico
            Boolean existDocument = Banco.getInstancia().getClientes().stream().anyMatch(item -> item.getDocumento().equals(cliente.getDocumento()));

            if(existDocument){
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "Ya se encuentra un registro con el numero de documento");
            }

            //Si no existe el documento agregar a la lista de clientes 
    
            Cliente newClient = new Cliente(cliente.getNombre(),cliente.getDocumento(),cliente.getEmail(),cliente.getTelefono(),true);

            Banco.getInstancia().getClientes().add(newClient);

            return ResponseHelper.response(HttpStatus.OK, true, newClient, "El cliente se creo exitosamente en el banco");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }

    
    @PutMapping("{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable UUID id, @Valid @RequestBody ClienteDto actualizarCliente, BindingResult result){

        if(result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{
            Cliente clientefound = Banco.getInstancia().getClientes().stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);

            if(clientefound == null){
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encuentran clientes con el id ingresado");
            }


            // ** validar si le van a cambiar el correo a el usuario
            if (!clientefound.getDocumento().equals(actualizarCliente.getDocumento())){

            //Validar numero de documento sea unico
            Boolean existDocument = Banco.getInstancia().getClientes().stream().anyMatch(item -> item.getDocumento().equals(actualizarCliente.getDocumento()));

            if(existDocument){
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "Ya se encuentra un registro con el numero de documento");

            }
            }

            clientefound.setNombre(actualizarCliente.getNombre());
            clientefound.setDocumento(actualizarCliente.getDocumento());
            clientefound.setEmail(actualizarCliente.getEmail());
            clientefound.setTelefono(actualizarCliente.getTelefono());

            return ResponseHelper.response(HttpStatus.OK, true, actualizarCliente, "Cliente actualizado correctamente");

        }


        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }

     }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable UUID id){

        try{

            Cliente clienteFound = Banco.getInstancia().getClientes().stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);

            if(clienteFound == null){
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "Cliente no encontrado");
            }

            Banco.getInstancia().getClientes().remove(clienteFound);
            return ResponseHelper.response(HttpStatus.OK, true, clienteFound, "Cliente eliminado correctamente");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }
    
    
}
