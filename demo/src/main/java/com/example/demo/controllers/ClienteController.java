package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import com.example.demo.dto.ClienteDto;
import com.example.demo.entity.Cliente;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")

public class ClienteController {
    
    private List<Cliente> clientes = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> listarClientes(){


        try{
            return ResponseHelper.response(HttpStatus.OK, true, clientes, "Listado de todos los clientes creados en el banco");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ListarClientesId(@PathVariable UUID id){

        try{

            Cliente clientesFound = clientes.stream().filter((item -> item.getId().equals(id))).findFirst().orElse(null);

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
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDto crearCliente, BindingResult result){

        if (result.hasErrors()){
            return ResponseHelper.validFields(result);
        }

        try{
            Cliente clienteNuevo = clientes.stream().filter(item -> item.getDocumento.crearCliente.getDocumento()).findFirst().orElse(null);

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }

}
