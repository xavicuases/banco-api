package com.bancoapi.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MovimientoReporteDTO {
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;

    public MovimientoReporteDTO(LocalDateTime fecha, String tipoMovimiento, Double valor, Double saldo) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;

    }

    // Getters y Setters

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
}

