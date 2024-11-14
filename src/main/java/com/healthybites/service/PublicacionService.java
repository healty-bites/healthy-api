package com.healthybites.service;

import com.healthybites.dto.PublicacionCreateDTO;
import com.healthybites.dto.PublicacionDTO;

import java.util.List;

public interface PublicacionService {
    List<PublicacionDTO> getAll(Integer id);
    PublicacionDTO findByIdAndClienteId(Integer publicacionId, Integer clienteId);
    PublicacionDTO create(PublicacionCreateDTO publicacionCreateDTO);
    PublicacionDTO update(Integer publicacionId, Integer clienteId, PublicacionCreateDTO updatedPublicacionDTO);
    void delete(Integer publicacionId, Integer clienteId);
}
