package com.bancoapi.service;


import com.bancoapi.dto.CuentaReporteDTO;
import com.bancoapi.dto.EstadoCuentaDTO;
import com.bancoapi.dto.MovimientoReporteDTO;
import com.bancoapi.exception.ResourceNotFoundException;
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

    // NUEVO MÉTODO LIMPIO (El que usaremos para el nuevo endpoint)
    // 1. REPORTE POR ID
    public EstadoCuentaDTO generarReporteLimpio(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId));

        return mapearADTO(cliente, fechaInicio, fechaFin);
    }

    // 2. REPORTE MASIVO (Para todos los clientes)
    public List<EstadoCuentaDTO> generarTodosLosReportesLimpio(LocalDateTime inicio, LocalDateTime fin) {
        return clienteRepository.findAll().stream()
                .map(cliente -> mapearADTO(cliente, inicio, fin))
                .toList();
    }

    // MÉTODO PRIVADO (Para no repetir la lógica del for en ambos métodos)
    private EstadoCuentaDTO mapearADTO(Cliente cliente, LocalDateTime inicio, LocalDateTime fin) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(cliente.getId());
        List<CuentaReporteDTO> cuentasReporte = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientosEntidad = movimientoRepository.findByCuentaNumeroCuentaAndFechaBetween(
                    cuenta.getNumeroCuenta(), inicio, fin);

            List<MovimientoReporteDTO> movimientosDTO = movimientosEntidad.stream()
                    .map(m -> new MovimientoReporteDTO(m.getFecha(), m.getTipoMovimiento(), m.getValor(), m.getSaldo()))
                    .toList();

            cuentasReporte.add(new CuentaReporteDTO(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta(), cuenta.getSaldo(), movimientosDTO));
        }
        return new EstadoCuentaDTO(cliente.getNombre(), cuentasReporte);
    }
}

