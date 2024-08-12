package com.example.Proyecto_final_bueno.Repository;


import com.example.Proyecto_final_bueno.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}

