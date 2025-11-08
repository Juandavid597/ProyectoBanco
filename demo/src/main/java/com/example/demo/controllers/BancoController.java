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
import com.example.demo.entity.Banco;
import com.example.demo.entity.Cliente;
import com.example.demo.helpers.ResponseHelper;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/banco")
public class BancoController {


    private static final double TASA_3_MESES = 0.05; // 5% EA
    private static final double TASA_6_MESES = 0.055; // 5.5% EA
    private static final double TASA_9_MESES = 0.058; // 5.8% EA
    private static final double TASA_12_MESES = 0.06; // 6% EA
    private static final double TASA_18_MESES = 0.065; // 6.5% EA
    private static final double TASA_24_MESES = 0.07; // 7% EA


   

    

   
    
    
}
