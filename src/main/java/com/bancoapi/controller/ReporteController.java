package com.bancoapi.controller;

import com.bancoapi.dto.EstadoCuentaDTO;
import com.bancoapi.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<?> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam LocalDateTime fechaInicio,
            @RequestParam LocalDateTime fechaFin) {

        return ResponseEntity.ok(
                reporteService.generarEstadoCuenta(clienteId, fechaInicio, fechaFin)
        );
    }
    // Endpoint Individual: /reportes/dto/1
    @GetMapping("/dto/{id}")
    public ResponseEntity<EstadoCuentaDTO> obtenerReportePorId(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        return ResponseEntity.ok(reporteService.generarReporteLimpio(id, fechaInicio, fechaFin));
    }

    // Endpoint Masivo: /reportes/dto
    @GetMapping("/dto")
    public ResponseEntity<List<EstadoCuentaDTO>> obtenerTodos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        return ResponseEntity.ok(reporteService.generarTodosLosReportesLimpio(fechaInicio, fechaFin));
    }

}
