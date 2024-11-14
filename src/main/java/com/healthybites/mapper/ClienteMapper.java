package com.healthybites.mapper;

import com.healthybites.dto.ClienteCreateDTO;
import com.healthybites.dto.ClienteDTO;
import com.healthybites.model.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    public Cliente toEntity(ClienteCreateDTO clienteCreateDTO) {
        return modelMapper.map(clienteCreateDTO, Cliente.class);
    }

    public void updateFromDTO(ClienteCreateDTO clienteCreateDTO, Cliente cliente) {
        modelMapper.map(clienteCreateDTO, cliente);
    }

}