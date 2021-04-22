package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transacciones")
public class Transaccion implements Serializable {

    @Id
    @Column(name = "numero")
    @GeneratedValue
    private Long numero;

    @Column(name = "numerocuenta")
    private String numeroCuenta;

    @Column(name ="tipo")
    private String tipo;

    @Column(name ="monto")
    private double monto;

    @Column(name = "fecha")
    private Date fecha;

    public Transaccion() {
        fecha = new Date();
    }

    public Transaccion(Long numero, String numeroCuenta, String tipo, double monto, Date fecha) {
        this.numero = numero;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
