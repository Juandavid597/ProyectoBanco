package com.example.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Banco;
import com.example.demo.entity.CDT;
import com.example.demo.entity.CDT;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;

import com.example.demo.dto.CdtDto;
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

    @PostMapping("/{numeroCuenta}")
    public ResponseEntity<?> crearCdt(@PathVariable String numeroCuenta, @Valid @RequestBody CdtDto cdt, BindingResult result) {
        if(result.hasErrors()){
            return ResponseHelper.validFields(result); 
        }
        try {
            CuentaAhorros cuentaFound=fakeDb.getCuentas().stream().filter(item->item.getNumeroCuenta().equals(numeroCuenta)).findFirst().orElse(null);
            if (cuentaFound==null) {
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "null", "No se encontro una cuenta asociada");
            }
            if(cuentas(getSaldo) < CdtDto.getMontoInvertido()){
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "null", "No hay saldo suficiente");
            }

            Cdt nuevoCDT = new Cdt(cdts.getCuentaAsociada(), cdts.getMontoInvertido(), cdts.getPlazoMeses(), cdts.getTasaEfectivaAnual(), cdts.getFechaAnual());


            
        } catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }
}
