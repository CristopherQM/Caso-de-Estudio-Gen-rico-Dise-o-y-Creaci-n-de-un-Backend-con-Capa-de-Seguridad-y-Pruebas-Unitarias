package com.example.Proyecto_final_bueno.dto;

public class UserDto {

    private long id;
    private String name;
    private String email;

    public UserDto(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
