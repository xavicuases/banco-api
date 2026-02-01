package com.bancoapi.service;

import com.bancoapi.model.Movimiento;
import com.bancoapi.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepo;

    public MovimientoService(MovimientoRepository movimientoRepo) {
        this.movimientoRepo = movimientoRepo;
    }

    // LISTAR
    public List<Movimiento> listar() {
        return movimientoRepo.findAll();
    }

    // OBTENER POR ID
    public Movimiento obtener(Long id) {
        return movimientoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }

    // CREAR
    public Movimiento crear(Movimiento m) {
        return movimientoRepo.save(m);
    }

    // ACTUALIZAR
    public Movimiento actualizar(Long id, Movimiento m) {
        Movimiento mov = obtener(id);

        mov.setFecha(m.getFecha());
        mov.setTipoMovimiento(m.getTipoMovimiento());
        mov.setValor(m.getValor());
        mov.setSaldo(m.getSaldo());
        mov.setCuenta(m.getCuenta());

        return movimientoRepo.save(mov);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        movimientoRepo.deleteById(id);
    }
}
