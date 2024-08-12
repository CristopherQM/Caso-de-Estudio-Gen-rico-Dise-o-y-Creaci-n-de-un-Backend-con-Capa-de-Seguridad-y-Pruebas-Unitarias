package com.example.Proyecto_final_bueno.Repository;

import com.example.Proyecto_final_bueno.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long >{
}
