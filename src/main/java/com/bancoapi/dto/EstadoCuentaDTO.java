package com.bancoapi.dto;

import java.util.List;

public class EstadoCuentaDTO {
    private String cliente;
    private List<CuentaReporteDTO> cuentas;

    public EstadoCuentaDTO(String cliente, List<CuentaReporteDTO> cuentas) {
        this.cliente = cliente;
        this.cuentas = cuentas;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<CuentaReporteDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaReporteDTO> cuentas) {
        this.cuentas = cuentas;
    }
}
