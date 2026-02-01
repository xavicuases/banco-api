package com.bancoapi.controller;
import com.bancoapi.model.Movimiento;
import com.bancoapi.service.MovimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    // ======================
    // LISTAR
    // ======================
    @GetMapping
    public List<Movimiento> listar() {
        return movimientoService.listar();
    }

    // ======================
    // OBTENER
    // ======================
    @GetMapping("/{id}")
    public Movimiento obtener(@PathVariable Long id) {
        return movimientoService.obtener(id);
    }

    // ======================
    // CREAR
    // ======================
    @PostMapping
    public Movimiento crear(@RequestBody Movimiento m) {
        return movimientoService.crear(m);
    }

    // ======================
    // ACTUALIZAR
    // ======================
    @PutMapping("/{id}")
    public Movimiento actualizar(@PathVariable Long id,
                                 @RequestBody Movimiento m) {
        return movimientoService.actualizar(id, m);
    }

    // ======================
    // ELIMINAR
    // ======================
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
    }
}
