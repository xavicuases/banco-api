package com.bancoapi.dto;

import java.util.List;

public class CuentaReporteDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoActual;
    private List<MovimientoReporteDTO> movimientos;

    public CuentaReporteDTO(String numeroCuenta, String tipoCuenta, Double saldoActual, List<MovimientoReporteDTO> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoActual = saldoActual;
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

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public List<MovimientoReporteDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoReporteDTO> movimientos) {
        this.movimientos = movimientos;
    }
}
