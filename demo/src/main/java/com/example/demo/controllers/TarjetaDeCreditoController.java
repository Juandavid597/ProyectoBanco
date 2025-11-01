package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Banco;

@RestController 
@RequestMapping("/TarjetaCredito")
@CrossOrigin("*")


public class TarjetaDeCreditoController {

    private static Banco instancia;



    
    
}
