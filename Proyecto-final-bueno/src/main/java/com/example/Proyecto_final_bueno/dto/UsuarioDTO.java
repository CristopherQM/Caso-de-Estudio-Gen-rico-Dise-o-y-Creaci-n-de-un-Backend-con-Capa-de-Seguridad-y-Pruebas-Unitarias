package com.example.Proyecto_final_bueno.dto;

import com.example.Proyecto_final_bueno.entity.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contraseña;  // Añadido para la contraseña
    private String direccion;
    private String telefono;
    private String rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;  // Devuelve la contraseña correctamente
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;  // Establece la contraseña
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



}
