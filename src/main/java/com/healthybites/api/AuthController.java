package com.healthybites.api;

import com.healthybites.model.entity.Usuario;
import com.healthybites.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.registerUsuario(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }
}
