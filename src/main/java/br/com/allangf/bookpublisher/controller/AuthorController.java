package br.com.allangf.bookpublisher.controller;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author/v1")
@AllArgsConstructor
@Tag(name = "Author")
public class AuthorController {

    @Autowired
    private AuthorService service;


    @Autowired
    private AuthorService authorService;

    @PostMapping
    @Operation(summary = "Create a new author")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find author by id")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        AuthorResponseDTO author = authorService.findById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Find Author by name")
    public ResponseEntity<AuthorResponseDTO> getAuthorByName(@PathVariable String name) {
        AuthorResponseDTO author = authorService.findByName(name);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update author")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO updatedAuthor = authorService.updateById(id, authorRequestDTO);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Find all author")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
