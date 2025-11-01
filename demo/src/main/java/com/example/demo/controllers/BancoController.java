package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Cliente;
import com.example.demo.helpers.ResponseHelper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/banco")
public class BancoController {
    List<Cliente> clientes = new ArrayList<>();
    

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        try {
            return ResponseHelper.response(HttpStatus.OK, true, clientes, "Este metodo debe mostrar la lista de clientes");
        } catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable String id) {
        try {
            Cliente clienteFound = clientes.stream().filter(item->item.getId().equals(UUID.fromString(id))).findFirst().orElse(null);
            if (clienteFound==null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, 
                "", "Cliente no encontrado");
            }
            return ResponseHelper.response(HttpStatus.OK, true, clienteFound, "Esta ruta muestra la información detallada del cliente");

        } catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }
}
