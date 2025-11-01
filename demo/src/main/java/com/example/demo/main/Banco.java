package com.example.demo.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.CDT;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.CuentaAhorros;
import com.example.demo.entity.TarjetaCredito;

public class Banco {
    

// En la clase Banco o clase principal
private List<Cliente> clientes = new ArrayList<>(); // si dejo sólo el listado acá no se puede llamar en el controller de cliente

// Listas opcionales para búsquedas rápidas o reportes globales
private List<CuentaAhorros> todasLasCuentas = new ArrayList<>();
private List<CDT> todosLosCDTs = new ArrayList<>();
private List<TarjetaCredito> todasLasTarjetas = new ArrayList<>();

// Para búsquedas rápidas (opcional pero recomendado)
private Map<String, Cliente> clientesPorDocumento = new HashMap<>();
private Map<String, CuentaAhorros> cuentasPorNumero = new HashMap<>();
private Map<String, TarjetaCredito> tarjetasPorNumero = new HashMap<>();


}
