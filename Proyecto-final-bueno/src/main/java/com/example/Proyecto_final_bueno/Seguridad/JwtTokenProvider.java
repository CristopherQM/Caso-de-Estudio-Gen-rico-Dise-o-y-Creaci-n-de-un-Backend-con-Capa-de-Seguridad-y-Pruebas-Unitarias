package com.example.Proyecto_final_bueno.Seguridad;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    // Implementación del JwtTokenProvider
    public String crearToken(String correoElectronico) {
        return correoElectronico;
    }
}
