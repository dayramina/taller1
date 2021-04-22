package edu.eam.ingesoft.ejemploback.services;

import edu.eam.ingesoft.ejemploback.model.Cliente;
import edu.eam.ingesoft.ejemploback.model.Cuenta;
import edu.eam.ingesoft.ejemploback.model.Transaccion;
import edu.eam.ingesoft.ejemploback.repositories.ClienteRepository;
import edu.eam.ingesoft.ejemploback.repositories.CuentaRepository;
import edu.eam.ingesoft.ejemploback.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;


    public  Cuenta crearCuenta(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findById(cuenta.getCedulaCliente()).orElse(null);

        if (cliente == null) {
            throw new RuntimeException("no existe el cliente");
        }

        List<Cuenta> cuentasCliente = cuentaRepository.buscarCuentasCliente(cuenta.getCedulaCliente());

        if (cuentasCliente.size() == 3) {
            throw new RuntimeException("supero el limite de cuentas");
        }

        cuentaRepository.save(cuenta);

        return cuenta;
    }

    public List<Cuenta> listarCuentasCliente(String cedula) {
        return cuentaRepository.buscarCuentasCliente(cedula);
    }

    public Cuenta consignarCuenta(String numeroCuenta, double monto) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElse(null);

        // Valido si la cuenta existe
        if (cuenta == null) {
            throw new RuntimeException("no existe la cuenta");
        }

        // Calculo nuevo monto de la cuenta
        double nuevoMonto = cuenta.getAmount() + monto;
        cuenta.setAmount(nuevoMonto);

        // Guardo cambios de la cuenta
        cuentaRepository.save(cuenta);

        //Guardo transaccion
        Transaccion transaccion = new Transaccion(null, cuenta.getId(), "consignación", monto, new Date());
        transaccionRepository.save(transaccion);

        return cuenta;
    }

    public Cuenta retirarCuenta(String numeroCuenta, double monto) {
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElse(null);

        // Valido si la cuenta existe
        if (cuenta == null) {
            throw new RuntimeException("no existe la cuenta");
        }

        // Calculo nuevo monto de la cuenta
        double nuevoMonto = cuenta.getAmount() - monto;
        cuenta.setAmount(nuevoMonto);

        // Guardo cambios de la cuenta
        cuentaRepository.save(cuenta);

        //Guardo transaccion
        Transaccion transaccion = new Transaccion(null, cuenta.getId(), "retiro", monto, new Date());
        transaccionRepository.save(transaccion);

        return cuenta;
    }

    public Cuenta transferirDineroCuenta(String numeroCuentaOrigen, String numeroCuentaDestino, double monto) {
        Cuenta cuentaOrigen = cuentaRepository.findById(numeroCuentaOrigen).orElse(null);
        Cuenta cuentaDestino = cuentaRepository.findById(numeroCuentaDestino).orElse(null);

        // Valido que las cuentas existan
        if (cuentaOrigen == null || cuentaDestino == null) {
            throw new RuntimeException("verifique la existencia de las cuentas");
        }

        // Calculo nuevo monto de la cuenta origen£
        double nuevoMontoCuentaOrigen = cuentaOrigen.getAmount() - monto;
        cuentaOrigen.setAmount(nuevoMontoCuentaOrigen);

        // Calculo nuevo monto de la cuenta destino
        double nuevoMontoCuentaDestino = cuentaDestino.getAmount() + monto;
        cuentaDestino.setAmount(nuevoMontoCuentaDestino);

        // Guardo cambios de la cuenta origen y destino
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        //Guardo transaccion
        Transaccion transaccion = new Transaccion(null, cuentaOrigen.getId(), "transferencia", monto, new Date());
        transaccionRepository.save(transaccion);

        return cuentaOrigen;
    }

    public void borrarCuenta(String numeroCuenta) {
        //orElse retorna el resultado o null si no hay resultado...
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta).orElse(null);

        // valido que la cuenta exista
        if (cuenta == null) {
            throw new RuntimeException("la cuenta no existe");
        }

        // valido que la cuenta este en 0
        if (cuenta.getAmount() != 0) {
            throw new RuntimeException("la cuenta no puede ser eliminada");
        }

        cuentaRepository.deleteById(numeroCuenta);

    }

}
