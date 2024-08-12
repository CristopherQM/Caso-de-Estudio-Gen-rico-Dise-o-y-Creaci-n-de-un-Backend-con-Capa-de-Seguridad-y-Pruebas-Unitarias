package com.example.Proyecto_final_bueno.service;


import com.example.Proyecto_final_bueno.Repository.UserRepository;
import com.example.Proyecto_final_bueno.dto.UserDto;
import com.example.Proyecto_final_bueno.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAll() {
        return this.userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public UserDto getById(Long id){
        return this.userRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public UserDto save(UserDto user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        UserEntity entitySaved = userRepository.save(entity);
        UserDto saved = this.toDto(entitySaved);
        return saved;
    }

    public UserDto update(UserDto user, Long id) {
        UserEntity entity = this.userRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setEmail(user.getEmail());
            entity.setName(user.getName());
            UserEntity entitySaved = this.userRepository.save(entity);
            return this.toDto(entitySaved);
        }
        return null; // Manejo de caso cuando la entidad no se encuentra
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    private UserDto toDto(UserEntity entity){
        return new UserDto(entity.getId(), entity.getName(), entity.getEmail());
    }
}
