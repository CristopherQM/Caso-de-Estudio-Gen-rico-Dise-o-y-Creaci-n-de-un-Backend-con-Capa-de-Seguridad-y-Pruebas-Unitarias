package com.example.Proyecto_final_bueno.Test;


import com.example.Proyecto_final_bueno.Repository.UsuarioRepository;
import com.example.Proyecto_final_bueno.dto.UsuarioDTO;
import com.example.Proyecto_final_bueno.entity.Usuario;
import com.example.Proyecto_final_bueno.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testObtenerUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan Perez");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        UsuarioDTO result = usuarioService.obtenerUsuario(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Juan Perez", result.getNombre());

        verify(usuarioRepository, times(1)).findById(1L);
    }
}
