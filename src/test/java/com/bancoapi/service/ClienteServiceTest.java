package com.bancoapi.service;

import com.bancoapi.dto.ClienteDTO;
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

    @Test
    @DisplayName("Debe llamar al repositorio para guardar un cliente")
    void crearClienteExitoso() {
        // 1. GIVEN: Preparamos el cliente que queremos crear
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Nuevo Usuario");
        nuevoCliente.setIdentificacion("1234567890");

        // Simulamos que el repositorio devuelve el mismo cliente al guardar
        when(clienteRepository.save(any(Cliente.class))).thenReturn(nuevoCliente);

        // 2. WHEN: Ejecutamos el método del servicio
        Cliente resultado = clienteService.crearCliente(nuevoCliente);

        // 3. THEN: Aplicamos Control de Calidad
        assertNotNull(resultado);
        assertEquals("Nuevo Usuario", resultado.getNombre());

        // LA PARTE CLAVE: Verificamos que el repositorio fue llamado exactamente 1 vez
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe mapear correctamente los datos de Cliente a ClienteDTO")
    void testConvertirADTO() {
        // 1. GIVEN: Una entidad Cliente con datos completos
        Cliente cliente = new Cliente();
        cliente.setId(44L);
        cliente.setNombre("Xavier Cuases");
        cliente.setIdentificacion("1712345678");
        cliente.setDireccion("Tulcán, Ecuador");
        cliente.setTelefono("0999999999");

        // 2. WHEN: Ejecutamos la conversión
        ClienteDTO dto = clienteService.convertirADTO(cliente);

        // 3. THEN: Verificamos que no se haya perdido ni un solo dato en el camino
        assertNotNull(dto);
        assertEquals(cliente.getId(), dto.getId());
        assertEquals(cliente.getNombre(), dto.getNombre());
        assertEquals(cliente.getIdentificacion(), dto.getIdentificacion());
        assertEquals(cliente.getDireccion(), dto.getDireccion());
        assertEquals(cliente.getTelefono(), dto.getTelefono());
    }
}