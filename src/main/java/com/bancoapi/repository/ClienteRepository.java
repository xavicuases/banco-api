package com.bancoapi.repository;
import com.bancoapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Cliente findByIdentificacion(String identificacion);
}
