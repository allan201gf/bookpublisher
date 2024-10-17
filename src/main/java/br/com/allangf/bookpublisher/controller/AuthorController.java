package br.com.allangf.bookpublisher.controller;

import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author/v1")
@AllArgsConstructor
@Tag(name = "Author", description = "Author operations")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService service;


    @Autowired
    private AuthorService authorService;

    @PostMapping
    @Operation(summary = "Create a new author")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        log.info("New request received at createAuthor, body: {}", authorRequestDTO.toString());
        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);
        log.info("Response at createAuthor: {}", createdAuthor);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find author by id")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        log.info("New request received at getAuthorById: {}", id);
        AuthorResponseDTO author = authorService.findById(id);
        log.info("Response at getAuthorById: {}", author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Find Author by name")
    public ResponseEntity<AuthorResponseDTO> getAuthorByName(@PathVariable String name) {
        log.info("New request received at getAuthorByName: {}", name);
        AuthorResponseDTO author = authorService.findByName(name);
        log.info("Response at getAuthorByName: {}", author);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update author")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        log.info("New request received at updateAuthor - id: {}, body: {}", id, authorRequestDTO);
        AuthorResponseDTO updatedAuthor = authorService.updateById(id, authorRequestDTO);
        log.info("Response at updateAuthor: {}", updatedAuthor);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.info("New request received at deleteAuthor: {}", id);
        authorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Find all author")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        log.info("New request received at getAllAuthors");
        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
        log.info("Response at getAllAuthors: {}", authors);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
