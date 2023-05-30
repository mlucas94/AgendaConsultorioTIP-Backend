package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.dtos.LoginAuthDTO;
import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid IntentoDeLoginDTO request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        Profesional profesional = (Profesional) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(profesional);
        LoginAuthDTO response = new LoginAuthDTO(profesional.getEmail(), accessToken);

        return ResponseEntity.ok().body(response);
    }
}
