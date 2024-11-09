package com.healthybites.service.impl;

import com.healthybites.dto.NutricionistaDTO;
import com.healthybites.mapper.NutricionistaMapper;
import com.healthybites.model.entity.Nutricionista;
import com.healthybites.repository.NutricionistaRepository;
import com.healthybites.service.AdminNutricionistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminNutricionistaServiceImpl implements AdminNutricionistaService {

    private final NutricionistaRepository nutricionistaRepository;
    private final NutricionistaMapper nutricionistaMapper;

    @Transactional(readOnly = true)
    @Override
    public List<NutricionistaDTO> getAll() {
        List<Nutricionista> nutricionistas = nutricionistaRepository.findAll();
        return nutricionistas.stream()
                .map(nutricionistaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NutricionistaDTO findById(Integer id) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Nutricionista con id " + id + " no existe"));
        return nutricionistaMapper.toDTO(nutricionista);
    }

    @Override
    public NutricionistaDTO create(NutricionistaDTO nutricionistaDTO) {
        nutricionistaRepository.findByNombreAndApellido(nutricionistaDTO.getNombre(), nutricionistaDTO.getApellido())
                .ifPresent(nutricionista -> {
                    throw new RuntimeException("El Nutricionista ya existe con el mismo nombre y apellido");
                });

        Nutricionista nutricionista = nutricionistaMapper.toEntity(nutricionistaDTO);
        nutricionista.setFechaCreacion(LocalDateTime.now());
        nutricionista.setFechaActualizacion(LocalDateTime.now());
        Nutricionista savedNutricionista = nutricionistaRepository.save(nutricionista);

        return nutricionistaMapper.toDTO(savedNutricionista);
    }

    @Override
    public NutricionistaDTO update(Integer id, NutricionistaDTO updatedNutricionistaDTO) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Nutricionista con id " + id + " no existe"));

        nutricionistaMapper.updateFromDTO(updatedNutricionistaDTO, nutricionista);

        nutricionista.setFechaActualizacion(LocalDateTime.now());

        Nutricionista updatedNutricionista = nutricionistaRepository.save(nutricionista);
        return nutricionistaMapper.toDTO(updatedNutricionista);
    }

    @Override
    public void delete(Integer id) {
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Nutricionista con id " + id + " no existe"));
        nutricionistaRepository.delete(nutricionista);
    }
}
