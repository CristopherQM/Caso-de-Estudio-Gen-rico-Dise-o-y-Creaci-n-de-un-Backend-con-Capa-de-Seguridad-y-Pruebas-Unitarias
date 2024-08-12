package com.example.Proyecto_final_bueno.Controller;

import com.example.Proyecto_final_bueno.dto.BookDto;
import com.example.Proyecto_final_bueno.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll(){
        List<BookDto> lista = this.bookService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Long id) {
        BookDto book = this.bookService.getById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookDto){
        BookDto saved = this.bookService.save(bookDto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto, @PathVariable("id") Long id){
        BookDto updated = this.bookService.update(bookDto, id);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
