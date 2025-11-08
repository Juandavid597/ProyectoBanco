package com.example.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Banco;

import jakarta.validation.Valid;

import com.example.demo.dto.ClienteDto;
import com.example.demo.dto.simularCdtDto;

@RestController
@RequestMapping("/cdt")
@CrossOrigin("*")

public class CdtController {

    private final Banco fakeDb = Banco.getInstancia();

    @GetMapping("{id}")
    public ResponseEntity<?> simularCdt(@Valid @RequestBody simularCdtDto montoInvertido) {

    }
}
