package com.example.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Banco;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;

import com.example.demo.dto.SimularCdtDto;

@RestController
@RequestMapping("/cdt")
@CrossOrigin("*")

public class CdtController {

    private final Banco fakeDb = Banco.getInstancia();

    @PostMapping("")
    public ResponseEntity<?> simularCdt(@Valid @RequestBody SimularCdtDto simulador, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseHelper.validFields(result);
        }
        try {
                double simuladorCalculo = simulador.getMontoInvertido()*fakeDb.obtenerTasaCdt(simulador.getPlazoMeses());
            
            return ResponseHelper.response(HttpStatus.OK, true, simuladorCalculo, "");
        } catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }
}
