package com.bancoapi.service;

import com.bancoapi.model.Cliente;
import com.bancoapi.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository; // El falso repositorio

    @InjectMocks
    private ClienteService clienteService; // El servicio real donde inyectamos el falso repo

    @Test
    @DisplayName("Debe retornar un cliente cuando el ID existe")
    void obtenerClienteExitoso() {
        // GIVEN: Preparamos el escenario
        Cliente mockCliente = new Cliente();
        mockCliente.setId(1L);
        mockCliente.setNombre("Xavier Cuases");

        // Configuramos el mock: "Cuando busquen el ID 1, devuelve el mockCliente"
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(mockCliente));

        // WHEN: Ejecutamos el método que estamos probando
        Cliente resultado = clienteService.obtener(1L);

        // THEN: Verificamos que los datos coincidan
        assertNotNull(resultado);
        assertEquals("Xavier Cuases", resultado.getNombre());
        assertEquals(1L, resultado.getId());

        // Verificamos que realmente se consultó al repositorio una vez
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el cliente no existe")
    void obtenerClienteFalla() {
        // GIVEN: El repositorio devolverá vacío para el ID 99
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN & THEN: Verificamos que lance la RuntimeException que programaste
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.obtener(99L);
        });

        assertEquals("Cliente no encontrado", exception.getMessage());
    }
}