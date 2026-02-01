package com.bancoapi.service;

import com.bancoapi.model.Cuenta;
import com.bancoapi.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> listar() {
        return cuentaRepository.findAll();
    }

    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtener(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public Cuenta actualizar(Long id, Cuenta cuenta) {
        Cuenta existente = obtener(id);

        existente.setNumeroCuenta(cuenta.getNumeroCuenta());
        existente.setTipoCuenta(cuenta.getTipoCuenta());
        existente.setSaldoInicial(cuenta.getSaldoInicial());
        existente.setEstado(cuenta.getEstado());
        existente.setCliente(cuenta.getCliente());

        return cuentaRepository.save(existente);
    }

    public void eliminar(Long id) {
        cuentaRepository.deleteById(id);
    }

}
