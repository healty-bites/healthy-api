package com.healthybites.api;

import com.healthybites.dto.SeguimientoCreateDTO;
import com.healthybites.dto.SeguimientoDTO;
import com.healthybites.service.SeguimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/meta/{metaId}/seguimientos")
@PreAuthorize("hasRole('CLIENTE')")
public class SeguimientoController {

    private final SeguimientoService seguimientoService;

    @GetMapping
    public ResponseEntity<List<SeguimientoDTO>> getAll(@PathVariable Integer metaId) {
        List<SeguimientoDTO> seguimientos = seguimientoService.getAll(metaId);
        return new ResponseEntity<>(seguimientos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SeguimientoDTO> create(@PathVariable Integer metaId, @Valid @RequestBody SeguimientoCreateDTO seguimientoCreateDTO) {
        SeguimientoDTO seguimientoDTO = seguimientoService.create(metaId, seguimientoCreateDTO);
        return new ResponseEntity<>(seguimientoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{seguimientoId}")
    public ResponseEntity<SeguimientoDTO> getById(@PathVariable Integer metaId, @PathVariable Integer seguimientoId) {
        SeguimientoDTO seguimiento = seguimientoService.findByIdAndMetaId(seguimientoId, metaId);
        return new ResponseEntity<>(seguimiento, HttpStatus.OK);
    }

    @PutMapping("/{seguimientoId}")
    public ResponseEntity<SeguimientoDTO> update(@PathVariable Integer metaId,
                                                 @PathVariable Integer seguimientoId,
                                                 @Valid @RequestBody SeguimientoCreateDTO updatedSeguimientoDTO) {
        SeguimientoDTO updatedSeguimiento = seguimientoService.update(metaId, seguimientoId, updatedSeguimientoDTO);
        return new ResponseEntity<>(updatedSeguimiento, HttpStatus.OK);
    }

    @DeleteMapping("/{seguimientoId}")
    public ResponseEntity<Void> delete(@PathVariable Integer metaId, @PathVariable Integer seguimientoId) {
        seguimientoService.delete(metaId, seguimientoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
