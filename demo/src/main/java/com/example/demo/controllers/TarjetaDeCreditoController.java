package com.example.demo.controllers;

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
import com.example.demo.dto.TarjetaCreditoDto;
import com.example.demo.dto.TarjetaCreditoDtoResponse;
import com.example.demo.entity.Banco;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.TarjetaCredito;
import com.example.demo.helpers.ResponseHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/TarjetaCredito")
@CrossOrigin("*")

// Solicitar número de cuenta
// Validar que la cuenta exista
// Verificar que NO tenga una tarjeta ya creada
// Crear objeto TarjetaCredito
// Asociar tarjeta al cliente
// Mostrar confirmación
// Validaciones obligatorias:

// ✅ Cuenta debe existir
// ✅ Cliente NO debe tener tarjeta previamente
// ✅ La cuenta debe tener mínimo 1 mes de antigüedad (opcional)

public class TarjetaDeCreditoController {

    private final Banco fakeDb = Banco.getInstancia();

    // Listar todas las tajetas de credito del banco

    @GetMapping
    public ResponseEntity<?> ListarTrajetasCredito() {

        try {

            return ResponseHelper.response(HttpStatus.OK, true, fakeDb.getTarjetas(),
                    "Listado de tarjetas de credito creadas en el banco");

        }

        catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }

    // buscar información de tarjeta por numero de documento
    @GetMapping("{documento}")
    public ResponseEntity<?> ListarTarjetaDocumento(@PathVariable String documento) {

        try {

            Cliente tarjetaFound = fakeDb.getClientes().stream().filter((item -> item.getDocumento().equals(documento)))
                    .findFirst().orElse(null);

            if (tarjetaFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "",
                        "No se encontro registro de cliente con el documento ingresado");
            }

            if (tarjetaFound.getTarjeta() == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "",
                        "El cliente no cuenta con tarjetas de credito");
            }

            return ResponseHelper.response(HttpStatus.OK, true, fakeDb.getTarjetas(),
                    "Registro de tarjeta de crédito del cliente" + fakeDb.getNombre());

        }

        catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }

    // Solicitar número de cuenta = OK
    // Validar que la cuenta exista = OK
    // Verificar que NO tenga una tarjeta ya creada =OK
    // Crear objeto TarjetaCredito
    // Asociar tarjeta al cliente
    // Mostrar confirmación
    // Validaciones obligatorias:

    // ✅ Cuenta debe existir
    // ✅ Cliente NO debe tener tarjeta previamente
    // ✅ La cuenta debe tener mínimo 1 mes de antigüedad (opcional)

    @PostMapping("/{documento}")
    public ResponseEntity<?> crearTarjeta(@PathVariable String documento, @Valid @RequestBody TarjetaCreditoDto tarjeta,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseHelper.validFields(result);
        }

        try {

            Cliente clienteFound = fakeDb.getClientes().stream().filter((item -> item.getDocumento().equals(documento)))
                    .findFirst().orElse(null);

            if (clienteFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "",
                        "No se encontro registro de cliente con el documento ingresado");
            }

            if (clienteFound.getCuenta() == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "",
                        "El cliente no tiene cuentas de ahorro en el banco");
            }

            if (clienteFound.getTarjeta() != null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "",
                        "El cliente ya tiene una tarjeta registrada en el banco");
            }

            
            TarjetaCredito newTarjeta = new TarjetaCredito(clienteFound, tarjeta.getCupoTotal(), 0.0, 0.0, tarjeta.getPagoMinimoPorcentaje(), true);


            clienteFound.setTarjeta(newTarjeta);

            fakeDb.getTarjetas().add(newTarjeta);

            TarjetaCreditoDtoResponse tarjetaCreditoDtoResponse=new TarjetaCreditoDtoResponse(newTarjeta.getId(), newTarjeta.getNumeroTarjeta(),newTarjeta.getTitular().getNombre(),newTarjeta.getCupoTotal(),newTarjeta.getCupoDisponible(),newTarjeta.getDeudaActual(),newTarjeta.getFechaEmision(),newTarjeta.getFechaVencimiento(),newTarjeta.getPagoMinimoPorcentaje(),newTarjeta.isActiva());

            return ResponseHelper.response(HttpStatus.OK, true, tarjetaCreditoDtoResponse,
                    "La tarjeta se creo exitosamente");


        }

        catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }

    @PutMapping("{docuemnto}")
    public ResponseEntity<?> actualizarCliente(@PathVariable String documento,
            @Valid @RequestBody ClienteDto actualizarCliente, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseHelper.validFields(result);
        }

        try {
            Cliente clientefound = fakeDb.getClientes().stream().filter(item -> item.getDocumento().equals(documento))
                    .findFirst().orElse(null);

            if (clientefound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "",
                        "No se encuentran clientes con el documento ingresado");
            }

            // ** validar si le van a cambiar el correo a el usuario
            if (!clientefound.getDocumento().equals(actualizarCliente.getDocumento())) {

                // Validar numero de documento sea unico
                Boolean existDocument = fakeDb.getClientes().stream()
                        .anyMatch(item -> item.getDocumento().equals(actualizarCliente.getDocumento()));

                if (existDocument) {
                    return ResponseHelper.response(HttpStatus.BAD_REQUEST, false, "",
                            "Ya se encuentra un registro con el numero de documento");

                }
            }

            clientefound.setNombre(actualizarCliente.getNombre());
            clientefound.setDocumento(actualizarCliente.getDocumento());
            clientefound.setEmail(actualizarCliente.getEmail());
            clientefound.setTelefono(actualizarCliente.getTelefono());

            return ResponseHelper.response(HttpStatus.OK, true, actualizarCliente, "Cliente actualizado correctamente");

        }

        catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }

    }

    @DeleteMapping("/{documento}")
    public ResponseEntity<?> eliminarCliente(@PathVariable String documento) {

        try {

            Cliente clienteFound = fakeDb.getClientes().stream().filter(item -> item.getDocumento().equals(documento))
                    .findFirst().orElse(null);

            if (clienteFound == null) {
                return ResponseHelper.response(HttpStatus.NOT_FOUND, false, "", "Cliente no encontrado");
            }

            fakeDb.getClientes().remove(clienteFound);
            return ResponseHelper.response(HttpStatus.OK, true, clienteFound, "Cliente eliminado correctamente");

        }

        catch (Exception e) {
            return ResponseHelper.catchResponse(e);
        }
    }

}
