package edu.eam.ingesoft.ejemploback.controllers;

import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// api rest.........
@RestController
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/accounts")
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta){
        return cuentaService.crearCuenta(cuenta);
    }

    @GetMapping("/customers/{cedula}/accounts")
    public List<Cuenta> listarCuentasCliente(@PathVariable String cedula) {
        return cuentaService.listarCuentasCliente(cedula);
    }

    @PostMapping("/accounts/consign/{numeroCuenta}/{monto}")
    public Cuenta consignarCuenta(@PathVariable String numeroCuenta, @PathVariable double monto){
        return cuentaService.consignarCuenta(numeroCuenta, monto);
    }

    @PostMapping("/accounts/remove/{numeroCuenta}/{monto}")
    public Cuenta retirarCuenta(@PathVariable String numeroCuenta, @PathVariable double monto){
        return cuentaService.retirarCuenta(numeroCuenta, monto);
    }

    @PostMapping("/accounts/transfer/{numeroCuentaOrigen}/{numeroCuentaDestino}/{monto}")
    public Cuenta transferirDineroCuenta(@PathVariable String numeroCuentaOrigen, @PathVariable String numeroCuentaDestino, @PathVariable double monto){
        return cuentaService.transferirDineroCuenta(numeroCuentaOrigen, numeroCuentaDestino, monto);
    }

    @DeleteMapping("/accounts/{numeroCuenta}")
    public void borrarCliente(@PathVariable String numeroCuenta) {
        cuentaService.borrarCuenta(numeroCuenta);
    }
}
