package com.example.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Banco;

import com.example.demo.helpers.ResponseHelper;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/banco")
public class BancoController {

    private final Banco fakeDb = Banco.getInstancia();

    @GetMapping("/cliente")
    public ResponseEntity<?> listarClientes() {
        try {
            return ResponseHelper.response(HttpStatus.OK, true, fakeDb.getClientes(), "Este metodo debe mostrar la lista de clientes");
        } catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }

    @GetMapping("/cuenta")
    public ResponseEntity<?> listarCuentasDeAhorro(){


        try{
            return ResponseHelper.response(HttpStatus.OK, true, fakeDb.getCuentas(), "Listado de todos los clientes creados en el banco");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }

    @GetMapping("/CDT")
    public ResponseEntity<?> listarCdts(){


        try{
            return ResponseHelper.response(HttpStatus.OK, true, fakeDb.getCdts(), "Listado de todos los Cdts realizados en el banco");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }
    

    
}
