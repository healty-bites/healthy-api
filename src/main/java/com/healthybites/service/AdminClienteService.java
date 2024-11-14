package com.healthybites.service;

import com.healthybites.dto.ClienteCreateDTO;
import com.healthybites.dto.ClienteDTO;

import java.util.List;

public interface AdminClienteService {
    List<ClienteDTO> getAll();
    ClienteDTO findById(Integer id);
    ClienteDTO create(ClienteCreateDTO clienteCreateDTO);
    ClienteDTO update(Integer id, ClienteCreateDTO updatedClienteCreateDTO);
    void delete(Integer id);
}
