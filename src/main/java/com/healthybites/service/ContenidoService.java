package com.healthybites.service;

import com.healthybites.dto.ContenidoCreateDTO;
import com.healthybites.dto.ContenidoDTO;

import java.util.List;

public interface ContenidoService {
    List<ContenidoDTO> getAll();
    ContenidoDTO findById(Integer id);
    ContenidoDTO create(ContenidoCreateDTO contenidoCreateUpdateDTO);
    ContenidoDTO update(Integer id, ContenidoCreateDTO updateContenidoDTO);
    void delete(Integer id);
}
