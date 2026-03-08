package com.bancoapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private String tipoMovimiento; // Ej: "DEPOSITO" o "RETIRO"

    private Double valor;

    private Double saldo; // saldo resultante después del movimiento

    // NUEVO → para recibir número de cuenta en el JSON
    @Transient
    private String numeroCuenta;

    // Para pasar el número de cuenta en reportes
    @JsonProperty("numeroCuenta")
    public String getNumeroCuentaReporte() {
        return cuenta != null ? cuenta.getNumeroCuenta() : numeroCuenta;
    }

    // Relación con Cuenta
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonIgnoreProperties({"movimientos" , "cliente"})
    private Cuenta cuenta;

    // ===== getters y setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}