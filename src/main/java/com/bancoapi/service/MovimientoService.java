package com.bancoapi.service;

import com.bancoapi.model.Movimiento;
import com.bancoapi.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import com.bancoapi.model.Cuenta;
import com.bancoapi.repository.CuentaRepository;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepo;
    private final CuentaRepository cuentaRepo;

    public MovimientoService(MovimientoRepository movimientoRepo,
                             CuentaRepository cuentaRepo) {
        this.movimientoRepo = movimientoRepo;
        this.cuentaRepo = cuentaRepo;
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

        Cuenta cuenta = cuentaRepo.findById(
                m.getCuenta().getId()
        ).orElseThrow(() -> new RuntimeException("Cuenta no existe"));

        double saldoActual = cuenta.getSaldo();
        double nuevoSaldo = saldoActual + m.getValor();

        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        cuenta.setSaldo(nuevoSaldo);
        cuentaRepo.save(cuenta);

        m.setFecha(LocalDateTime.now());
        m.setSaldo(nuevoSaldo);

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
