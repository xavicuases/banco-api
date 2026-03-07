package com.bancoapi.controller;
import com.bancoapi.dto.ClienteDTO;
import com.bancoapi.model.Cliente;
import com.bancoapi.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<?> obtenerDTO(@PathVariable Long id) {

        try {
            Cliente cliente = clienteService.obtener(id);

            ClienteDTO dto = clienteService.convertirADTO(cliente);

            return ResponseEntity.ok(dto);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body("Cliente no encontrado o no existe");
        }
    }
    @GetMapping("/dto")
    public ResponseEntity<List<ClienteDTO>> listarClientesDTO() {

        List<Cliente> clientes = clienteService.obtenerClientes();

        List<ClienteDTO> clientesDTO = clientes.stream()
                .map(clienteService::convertirADTO)
                .toList();

        return ResponseEntity.ok(clientesDTO);
    }
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
