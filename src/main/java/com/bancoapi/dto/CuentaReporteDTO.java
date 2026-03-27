package com.bancoapi.dto;

import java.util.List;

public class CuentaReporteDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldo;
    private List<MovimientoReporteDTO> movimientos;

    public CuentaReporteDTO(String numeroCuenta, String tipoCuenta, Double saldo, List<MovimientoReporteDTO> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.movimientos = movimientos;
    }

    // Getters y Setters

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public List<MovimientoReporteDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoReporteDTO> movimientos) {
        this.movimientos = movimientos;
    }
}
