package com.bancoapi.controller;
import com.bancoapi.model.Cuenta;
import com.bancoapi.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> listar() {
        return cuentaService.listar();
    }

    @PostMapping
    public Cuenta crear(@RequestBody Cuenta cuenta) {
        return cuentaService.guardar(cuenta);
    }

    @GetMapping("/{id}")
    public Cuenta obtener(@PathVariable Long id) {
        return cuentaService.obtener(id);
    }

    @PutMapping("/{id}")
    public Cuenta actualizar(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        return cuentaService.actualizar(id, cuenta);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
    }

}
