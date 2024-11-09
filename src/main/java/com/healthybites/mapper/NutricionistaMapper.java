package com.healthybites.mapper;

import com.healthybites.dto.NutricionistaDTO;
import com.healthybites.model.entity.Nutricionista;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NutricionistaMapper {

    private final ModelMapper modelMapper;

    public NutricionistaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NutricionistaDTO toDTO(Nutricionista nutricionista) {
        return modelMapper.map(nutricionista, NutricionistaDTO.class);
    }

    public Nutricionista toEntity(NutricionistaDTO nutricionistaDTO) {
        return modelMapper.map(nutricionistaDTO, Nutricionista.class);
    }

    public void updateFromDTO(NutricionistaDTO nutricionistaDTO, Nutricionista nutricionista) {
        modelMapper.map(nutricionistaDTO, nutricionista);
    }
}
