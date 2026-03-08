package com.bancoapi.repository;
import com.bancoapi.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository <Movimiento, Long> {
    List<Movimiento> findByCuentaId(Long cuentaId);
    List<Movimiento> findByCuentaNumeroCuentaAndFechaBetween(
            String numeroCuenta,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );

}
