package com.healthybites.api;

import com.healthybites.dto.ClienteCreateDTO;
import com.healthybites.dto.ClienteDTO;
import com.healthybites.service.AdminClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/cliente")
@PreAuthorize("hasRole('ADMIN')")
public class AdminClienteController {

    private final AdminClienteService adminClienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllCliente() {
        List<ClienteDTO> clientes = adminClienteService.getAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
        ClienteDTO newCliente = adminClienteService.create(clienteCreateDTO);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Integer id) {
        ClienteDTO cliente = adminClienteService.findById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
        ClienteDTO updatedCliente = adminClienteService.update(id, clienteCreateDTO);
        return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        adminClienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}