package com.example.Proyecto_final_bueno.Controller;


import com.example.Proyecto_final_bueno.dto.BookDto;
import com.example.Proyecto_final_bueno.dto.UserDto;
import com.example.Proyecto_final_bueno.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuarios")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> lista = this.userService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        UserDto usuario = this.userService.getById(id);
        return ResponseEntity.ok(usuario);
    }


    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        UserDto saved = this.userService.save(userDto);
        return ResponseEntity.ok(saved); // Retorna el DTO guardado
    }

    @PutMapping("/")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable("id") Long id){
        UserDto updated = this.userService.update(userDto, id);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
