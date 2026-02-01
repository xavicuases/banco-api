package com.bancoapi.repository;
import com.bancoapi.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository <Cuenta, Long> {
    Cuenta findByNumeroCuenta(String numeroCuenta);
}
