package com.healthybites.repository;

import com.healthybites.model.entity.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {

    Optional<Contenido> findByTitulo(String titulo);

    Optional<Contenido> findByTipoContenido(String tipoContenido);
    Optional<Contenido> findByCategoriaContenido(String categoriaContenido);
}
