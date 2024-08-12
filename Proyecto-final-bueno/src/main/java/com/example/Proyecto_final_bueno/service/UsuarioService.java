package com.example.Proyecto_final_bueno.service;

import com.example.Proyecto_final_bueno.Repository.UsuarioRepository;
import com.example.Proyecto_final_bueno.Seguridad.JwtTokenProvider;
import com.example.Proyecto_final_bueno.dto.HistorialDTO;
import com.example.Proyecto_final_bueno.dto.LoginDTO;
import com.example.Proyecto_final_bueno.dto.TokenDTO;
import com.example.Proyecto_final_bueno.dto.UsuarioDTO;
import com.example.Proyecto_final_bueno.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getContraseña() == null || usuarioDTO.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
        }

        // Crear una nueva entidad Usuario y mapear los campos del DTO
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        usuario.setContraseña(passwordEncoder.encode(usuarioDTO.getContraseña()));
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setRol("CLIENTE");

        // Guardar la entidad en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir la entidad guardada en DTO y devolverla
        return this.convertirAUsuarioDTO(usuarioGuardado);
    }

    public List<UsuarioDTO> obtenerTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(this::convertirAUsuarioDTO).collect(Collectors.toList());
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public HistorialDTO obtenerHistorial(Long id) {
        return new HistorialDTO(); // Cambia esto según tu lógica
    }

    public TokenDTO autenticarUsuario(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(loginDTO.getCorreoElectronico())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (passwordEncoder.matches(loginDTO.getContraseña(), usuario.getContraseña())) {
            String token = jwtTokenProvider.crearToken(usuario.getCorreoElectronico());
            return new TokenDTO(token);
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }

    public UsuarioDTO obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertirAUsuarioDTO(usuario);
    }

    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreoElectronico(usuarioDTO.getCorreoElectronico());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setTelefono(usuarioDTO.getTelefono());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return convertirAUsuarioDTO(usuarioActualizado);
    }

    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioDTO.setDireccion(usuario.getDireccion());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setRol(usuario.getRol());
        return usuarioDTO;
    }
}
