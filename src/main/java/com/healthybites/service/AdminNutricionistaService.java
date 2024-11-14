package com.healthybites.service;

import com.healthybites.dto.NutricionistaDTO;

import java.util.List;

public interface AdminNutricionistaService {
    List<NutricionistaDTO> getAll();
    NutricionistaDTO findById(Integer id);
    NutricionistaDTO create(NutricionistaDTO nutricionistaDTO);
    NutricionistaDTO update(Integer id, NutricionistaDTO updatedNutricionistaDTO);
    void delete(Integer id);
}
