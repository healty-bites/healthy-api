package com.healthybites.api;

import com.healthybites.dto.AuthResponseDTO;
import com.healthybites.dto.LoginDTO;
import com.healthybites.dto.UserProfileDTO;
import com.healthybites.dto.UserRegistrationDTO;
import com.healthybites.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    // Registro de un cliente
    @PostMapping("/register/cliente")
    public ResponseEntity<UserProfileDTO> registerClient(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfileDTO = usuarioService.registrarCliente(userRegistrationDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.CREATED);
    }

    // Registro de un nutricionista
    @PostMapping("/register/nutricionista")
    public ResponseEntity<UserProfileDTO> registerNutritionist(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfileDTO = usuarioService.registrarNutricionista(userRegistrationDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponse = usuarioService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
