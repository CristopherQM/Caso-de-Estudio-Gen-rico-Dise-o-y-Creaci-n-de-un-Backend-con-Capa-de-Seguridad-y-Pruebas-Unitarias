package com.example.Proyecto_final_bueno.service;

import com.example.Proyecto_final_bueno.Repository.BookRepository;
import com.example.Proyecto_final_bueno.dto.BookDto;
import com.example.Proyecto_final_bueno.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDto> getAll() {
        return this.bookRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }


    public Optional<BookEntity> obtenerLibro(Long libroId) {
        return bookRepository.findById(libroId);
    }


    public BookDto getById(Long id){
        return this.bookRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public BookDto save(BookDto bookDto) {
        BookEntity entity = new BookEntity();
        entity.setTitle(bookDto.getTitle());
        entity.setAuthor(bookDto.getAuthor());
        entity.setIsbn(bookDto.getIsbn());
        entity.setAvailable(bookDto.isAvailable());
        BookEntity savedEntity = bookRepository.save(entity);
        return this.toDto(savedEntity);
    }

    public BookDto update(BookDto bookDto, Long id) {
        BookEntity entity = this.bookRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setTitle(bookDto.getTitle());
            entity.setAuthor(bookDto.getAuthor());
            entity.setIsbn(bookDto.getIsbn());
            entity.setAvailable(bookDto.isAvailable());
            BookEntity updatedEntity = this.bookRepository.save(entity);
            return this.toDto(updatedEntity);
        }
        return null;
    }

    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    private BookDto toDto(BookEntity entity){
        return new BookDto(entity.getId(), entity.getTitle(), entity.getAuthor(), entity.getIsbn(), entity.isAvailable());
    }
}
