package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.AuthRequest;
import com.unqttip.agendaprofesional.dtos.AuthResponse;
import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.services.ProfesionalService;
import com.unqttip.agendaprofesional.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
public class ProfesionalController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        Profesional profesional = (Profesional) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(profesional);
        AuthResponse response = new AuthResponse(profesional.getEmail(), accessToken);

        return ResponseEntity.ok().body(response);
    }
}
