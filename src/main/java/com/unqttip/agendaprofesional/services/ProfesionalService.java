package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.dtos.LoginAuthDTO;
import com.unqttip.agendaprofesional.dtos.NuevoProfesionalDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.repositories.ProfesionalDAO;
import com.unqttip.agendaprofesional.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;
    @Autowired
    private ProfesionalDAO profesionalDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginAuthDTO login(IntentoDeLoginDTO intentoDeLoginDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        intentoDeLoginDTO.getEmail(), intentoDeLoginDTO.getPassword())
        );

        Profesional profesional = (Profesional) authentication.getPrincipal();
        String accessToken = jwtUtil.generateAccessToken(profesional);
        return new LoginAuthDTO(profesional.getEmail(), accessToken);
    }

    public void registrar(NuevoProfesionalDTO nuevoProfesionalDTO) {
        List<String> emailsNoDisponibles = profesionalDAO.findAllEmails();
        if (emailsNoDisponibles.contains(nuevoProfesionalDTO.getEmail())) {
            throw new BadRequestException("El email " + nuevoProfesionalDTO.getEmail() + " ya está registrado");
        }
        Profesional nuevoProfesional = Profesional.builder()
                .email(nuevoProfesionalDTO.getEmail())
                .nombre(nuevoProfesionalDTO.getNombre())
                .password(passwordEncoder.encode(nuevoProfesionalDTO.getPassword()))
                .build();
        profesionalDAO.save(nuevoProfesional);
    }
}
