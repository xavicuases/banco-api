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

        Cuenta cuenta = cuentaRepo
                .findByNumeroCuenta(m.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double saldoActual = cuenta.getSaldo();
        double nuevoSaldo = saldoActual;

        if (m.getTipoMovimiento().equalsIgnoreCase("RETIRO")) {

            if (saldoActual < m.getValor()) {
                throw new RuntimeException("Saldo no disponible");
            }

            nuevoSaldo = saldoActual - m.getValor();

            m.setTipoMovimiento("Retiro de " + m.getValor());

        }
        else if (m.getTipoMovimiento().equalsIgnoreCase("DEPOSITO")) {

            nuevoSaldo = saldoActual + m.getValor();

            m.setTipoMovimiento("Deposito de " + m.getValor());

        }
        else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }

        cuenta.setSaldo(nuevoSaldo);
        cuentaRepo.save(cuenta);

        m.setFecha(LocalDateTime.now());
        m.setSaldo(nuevoSaldo);
        m.setCuenta(cuenta);

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
