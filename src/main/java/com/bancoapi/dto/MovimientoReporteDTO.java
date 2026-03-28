package com.bancoapi.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MovimientoReporteDTO {
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoResultante;

    public MovimientoReporteDTO(LocalDateTime fecha, String tipoMovimiento, Double valor, Double saldoResultante) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldoResultante = saldoResultante;

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

    public Double getSaldoResultante() {
        return saldoResultante;
    }

    public void setSaldoResultante(Double saldoResultante) {
        this.saldoResultante = saldoResultante;
    }
}

