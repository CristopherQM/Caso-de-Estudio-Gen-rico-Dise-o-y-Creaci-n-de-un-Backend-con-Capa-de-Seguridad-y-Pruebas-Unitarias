package com.example.Proyecto_final_bueno.Controller;

import com.example.Proyecto_final_bueno.dto.HistorialDTO;
import com.example.Proyecto_final_bueno.dto.LoginDTO;
import com.example.Proyecto_final_bueno.dto.TokenDTO;
import com.example.Proyecto_final_bueno.dto.UsuarioDTO;
import com.example.Proyecto_final_bueno.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        try {
            List<UsuarioDTO> usuarios = usuarioService.obtenerTodosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        try {
            UsuarioDTO usuario = usuarioService.obtenerUsuario(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioGuardado = usuarioService.registrarUsuario(usuarioDTO);
        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
            if (usuarioActualizado != null) {
                return ResponseEntity.ok(usuarioActualizado);
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            boolean eliminado = usuarioService.eliminarUsuario(id);
            if (eliminado) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenDTO> autenticarUsuario(@RequestBody LoginDTO loginDTO) {
        try {
            TokenDTO token = usuarioService.autenticarUsuario(loginDTO);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<HistorialDTO> obtenerHistorial(@PathVariable Long id) {
        try {
            HistorialDTO historialDTO = usuarioService.obtenerHistorial(id);
            return ResponseEntity.ok(historialDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
