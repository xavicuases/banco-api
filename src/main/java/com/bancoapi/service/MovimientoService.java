package com.bancoapi.service;

import com.bancoapi.model.Movimiento;
import com.bancoapi.repository.MovimientoRepository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional // Garantiza que se guarden ambos o ninguno
    public Movimiento crear(Movimiento m) {
        // 1. Buscamos la cuenta (Si no existe, el GlobalHandler lanzará el 404)
        Cuenta cuenta = cuentaRepo.findByNumeroCuenta(m.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        double saldoActual = cuenta.getSaldo();
        double valorMovimiento = m.getValor();
        double nuevoSaldo;

        // 2. Lógica de negocio para depósitos y retiros
        if (m.getTipoMovimiento().equalsIgnoreCase("RETIRO")) {
            if (saldoActual < valorMovimiento) {
                throw new RuntimeException("Saldo no disponible");
            }
            nuevoSaldo = saldoActual - valorMovimiento;
            // Opcional: mantener el tipo original pero con descripción si tienes el campo
        } else if (m.getTipoMovimiento().equalsIgnoreCase("DEPOSITO")) {
            nuevoSaldo = saldoActual + valorMovimiento;
        } else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }

        // 3. Actualizamos la cuenta
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepo.save(cuenta);

        // 4. Preparamos y guardamos el movimiento
        m.setFecha(LocalDateTime.now());
        m.setSaldo(nuevoSaldo); // Saldo que quedó después de la operación
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
