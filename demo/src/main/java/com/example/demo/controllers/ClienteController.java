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
import com.example.demo.entity.Cliente;
import com.example.demo.helpers.ResponseHelper;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")

public class ClienteController {
    
    private List<Cliente> clientes = new ArrayList<>();

    @GetMapping("/listar")
    public ResponseEntity<?> listarClientes(){


        try{
            return ResponseHelper.response(HttpStatus.OK, true, clientes, "Listado de todos los clientes creados en el banco");

        }

        catch(Exception e){
            return ResponseHelper.catchResponse(e);
        }

    }

    @GetMapping("listar/{documento}") //buscar información de cuenta por numero de documento
    public ResponseEntity<?> ListarClientesDocumento(@PathVariable String documento){

        try{

            Cliente clientesFound = clientes.stream().filter((item -> item.getDocumento().equals(documento))).findFirst().orElse(null);

            if (clientesFound == null){
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "No se encontro registro de cliente con el Id");
            }

            return ResponseHelper.response(HttpStatus.OK, true, clientesFound, "Cliente encontrado con ID");

        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }


    @PostMapping("crear")
    public ResponseEntity<?> crearCuentaAhorros(@Valid @RequestBody ClienteDto cliente, BindingResult result){
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
            Boolean existDocument = clientes.stream().anyMatch(item -> item.getDocumento().equals(cliente.getDocumento()));

            if(existDocument){
                return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "", "Ya se encuentra un registro con el numero de documento");
            }

            //Si no existe el documento agregar a la lista de clientes 
            //VALIDAR SI SALDO ENTRA TAMBIEN EN LA LISTA DE CLIENTES O CÓMO GUARDARLO EN LA LISTA DE CUENTA DE AHORROS
            Cliente newClient = new Cliente(cliente.getNombre(),cliente.getDocumento(),cliente.getEmail(),cliente.getTelefono(),cliente.getSaldo());

            clientes.add(newClient);

            return ResponseHelper.response(HttpStatus.OK, true, newClient, "Se creo exitosamente el cleinte en el banco y se asigno una cuenta de ahorros");



        }

        catch (Exception e){
            return ResponseHelper.catchResponse(e);
        }
    }

    
    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable UUID id, @Valid @RequestBody ClienteDto cliente, BindingResult result){



    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> eliminarCliente(@PathVariable UUID id){

    // }

}


// // En la clase Banco o clase principal
// private List<Cliente> clientes = new ArrayList<>();

// // Listas opcionales para búsquedas rápidas o reportes globales
// private List<CuentaAhorros> todasLasCuentas = new ArrayList<>();
// private List<CDT> todosLosCDTs = new ArrayList<>();
// private List<TarjetaCredito> todasLasTarjetas = new ArrayList<>();

// // Para búsquedas rápidas (opcional pero recomendado)
// private Map<String, Cliente> clientesPorDocumento = new HashMap<>();
// private Map<String, CuentaAhorros> cuentasPorNumero = new HashMap<>();
// private Map<String, TarjetaCredito> tarjetasPorNumero = new HashMap<>();