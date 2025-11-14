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
            
            return ResponseHelper.response(HttpStatus.OK, true, simuladorCalculo, "Este metodo muestra");
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
            Boolean cuentaFound=fakeDb.getClientes().stream().anyMatch(item->item.getCuenta().equals(cdt.getCuentaAsociada()));
            if (cuentaFound && cdt.getMontoInvertido() > 500000) {

                double tasaCDT = 1+fakeDb.obtenerTasaCdt(cdt.getPlazoMeses());
                double potenciaCDT = (cdt.getPlazoMeses()/12);
                double tasaConvertida = Math.pow(tasaCDT,potenciaCDT);

                double gananciaBruta = cdt.getMontoInvertido() * tasaConvertida;
                double retencion = gananciaBruta * 0.04;
                double gananciaNeta = gananciaBruta - retencion;
                double totalRecibir =  cdt.getMontoInvertido() + gananciaNeta;

                CDT nuevoCdt = new CDT(cdt.getCuentaAsociada(), cdt.getMontoInvertido(), cdt.getPlazoMeses(), fakeDb.obtenerTasaCdt(cdt.getPlazoMeses()), cdt.getFechaVencimiento(), gananciaBruta, retencion, gananciaNeta, totalRecibir, true,"Activo" );

                return ResponseHelper.response(HttpStatus.OK, true, nuevoCdt, "Se muestra la cuenta asociada");
            }

            return ResponseHelper.response(HttpStatus.BAD_REQUEST, true, "", "No se encontro la cuenta o el monto no es valido");
        } catch (Exception e) {
            return ResponseHelper.catchResponse(e); 
        }


    }
}
