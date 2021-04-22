package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("/accounts/{numeroCuenta}/transaccions")
    public List<Transaccion> listarTransaccionesCuenta(@PathVariable String numeroCuenta) {
        return transaccionService.listarTransaccionesCuenta(numeroCuenta);
    }
}
