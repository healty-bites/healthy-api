package com.healthybites.dto;

import com.healthybites.model.enums.CategoriaContenido;
import com.healthybites.model.enums.TipoContenido;
import lombok.Data;

@Data
public class ContenidoDetailsDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private TipoContenido tipoContenido;
    private CategoriaContenido categoriaContenido;
    private boolean esGratis;
    private String nutricionistaNombre;
}
