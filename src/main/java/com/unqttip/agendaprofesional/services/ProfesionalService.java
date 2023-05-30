package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.dtos.LoginAuthDTO;
import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ProfesionalService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;

    public LoginAuthDTO login(IntentoDeLoginDTO intentoDeLoginDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        intentoDeLoginDTO.getEmail(), intentoDeLoginDTO.getPassword())
        );

        Profesional profesional = (Profesional) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(profesional);
        return new LoginAuthDTO(profesional.getEmail(), accessToken);
    }
}
