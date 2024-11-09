package com.healthybites.service.impl;

import com.healthybites.dto.ContenidoCreateDTO;
import com.healthybites.dto.ContenidoDTO;
import com.healthybites.exception.BadRequestException;
import com.healthybites.exception.ResourceNotFoundException;
import com.healthybites.mapper.ContenidoMapper;
import com.healthybites.model.entity.Contenido;
import com.healthybites.model.entity.Nutricionista;
import com.healthybites.repository.ContenidoRepository;
import com.healthybites.repository.NutricionistaRepository;
import com.healthybites.service.ContenidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContenidoServiceImpl implements ContenidoService {

    private final ContenidoRepository contenidoRepository;
    private final NutricionistaRepository nutricionistaRepository;
    private final ContenidoMapper contenidoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ContenidoDTO> getAll() {
        List<Contenido> contenidos = contenidoRepository.findAll();
        return contenidos.stream()
                .map(contenidoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ContenidoDTO findById(Integer id) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido con id " + id + " no encontrado"));
        return contenidoMapper.toDTO(contenido);
    }

    @Override
    public ContenidoDTO create(ContenidoCreateDTO contenidoCreateUpdateDTO) {
        contenidoRepository.findByTitulo(contenidoCreateUpdateDTO.getTitulo())
                .ifPresent(contenido -> {
                    throw new BadRequestException("Contenido con titulo " + contenidoCreateUpdateDTO.getTitulo() + " ya existe");
                });

        Nutricionista nutricionista = nutricionistaRepository.findById(contenidoCreateUpdateDTO.getNutricionistaId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista con id " + contenidoCreateUpdateDTO.getNutricionistaId() + " no encontrado"));

        Contenido contenido = contenidoMapper.toEntity(contenidoCreateUpdateDTO);

        contenido.setNutricionista(nutricionista);
        contenido.setFechaCreacion(LocalDateTime.now());
        contenido.setFechaActualizacion(LocalDateTime.now());

        return contenidoMapper.toDTO(contenidoRepository.save(contenido));
    }

    @Override
    public ContenidoDTO update(Integer id, ContenidoCreateDTO updateContenidoDTO) {
        // Find the contenido
        Contenido contenidoFromDb = contenidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido con id " + id + " no encontrado"));

        contenidoRepository.findByTitulo(updateContenidoDTO.getTitulo())
                .ifPresent(contenidoExistente -> {
                    throw new BadRequestException("Contenido con titulo " + updateContenidoDTO.getTitulo() + " ya existe");
                });

        // Find the nutricionista
        Nutricionista nutricionista = nutricionistaRepository.findById(updateContenidoDTO.getNutricionistaId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutricionista con id " + updateContenidoDTO.getNutricionistaId() + " no encontrado"));

        // Update the fields
        contenidoFromDb.setTitulo(updateContenidoDTO.getTitulo());
        contenidoFromDb.setDescripcion(updateContenidoDTO.getDescripcion());
        contenidoFromDb.setTipoContenido(updateContenidoDTO.getTipoContenido());
        contenidoFromDb.setCategoriaContenido(updateContenidoDTO.getCategoriaContenido());
        contenidoFromDb.setEsGratis(updateContenidoDTO.isEsGratis());
        contenidoFromDb.setNutricionista(nutricionista);

        return contenidoMapper.toDTO(contenidoRepository.save(contenidoFromDb));
    }

    @Override
    public void delete(Integer id) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contenido con id " + id + " no encontrado"));
        contenidoRepository.delete(contenido);
    }
}
