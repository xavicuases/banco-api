package com.bancoapi.service;


import com.bancoapi.model.Cliente;
import com.bancoapi.model.Cuenta;
import com.bancoapi.model.Movimiento;
import com.bancoapi.repository.ClienteRepository;
import com.bancoapi.repository.CuentaRepository;
import com.bancoapi.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteService {
    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public ReporteService(
            ClienteRepository clienteRepository,
            CuentaRepository cuentaRepository,
            MovimientoRepository movimientoRepository) {

        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public Map<String, Object> generarEstadoCuenta(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

        Map<String, Object> reporte = new HashMap<>();
        reporte.put("cliente", cliente.getNombre());

        List<Map<String, Object>> cuentasReporte = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {

            Map<String, Object> cuentaData = new HashMap<>();
            cuentaData.put("numeroCuenta", cuenta.getNumeroCuenta());
            cuentaData.put("tipoCuenta", cuenta.getTipoCuenta());
            cuentaData.put("saldo", cuenta.getSaldo());

            List<Movimiento> movimientos =
                    movimientoRepository.findByCuentaNumeroCuentaAndFechaBetween(
                            cuenta.getNumeroCuenta(),
                            fechaInicio,
                            fechaFin
                    );

            cuentaData.put("movimientos", movimientos);

            cuentasReporte.add(cuentaData);
        }

        reporte.put("cuentas", cuentasReporte);

        return reporte;
    }
}

