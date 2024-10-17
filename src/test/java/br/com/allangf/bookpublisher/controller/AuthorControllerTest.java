package br.com.allangf.bookpublisher.controller;

import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private AuthorRequestDTO authorRequestDTO;
    private AuthorResponseDTO authorResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorRequestDTO = new AuthorRequestDTO();
        authorRequestDTO.setName("Allan");

        authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setId(1L);
        authorResponseDTO.setName("Allan");
    }

    @Test
    void ShouldCreateAuthor() {
        when(authorService.createAuthor(any(AuthorRequestDTO.class))).thenReturn(authorResponseDTO);

        ResponseEntity<AuthorResponseDTO> response = authorController.createAuthor(authorRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(authorResponseDTO, response.getBody());
        verify(authorService).createAuthor(authorRequestDTO);
    }

    @Test
    void ShouldGetAuthorById() {
        when(authorService.findById(1L)).thenReturn(authorResponseDTO);

        ResponseEntity<AuthorResponseDTO> response = authorController.getAuthorById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorResponseDTO, response.getBody());
        verify(authorService).findById(1L);
    }

    @Test
    void ShouldGetAuthorByName() {
        when(authorService.findByName("Allan")).thenReturn(authorResponseDTO);

        ResponseEntity<AuthorResponseDTO> response = authorController.getAuthorByName("Allan");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorResponseDTO, response.getBody());
        verify(authorService).findByName("Allan");
    }

    @Test
    void ShouldUpdateAuthor() {
        when(authorService.updateById(1L, authorRequestDTO)).thenReturn(authorResponseDTO);

        ResponseEntity<AuthorResponseDTO> response = authorController.updateAuthor(1L, authorRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorResponseDTO, response.getBody());
        verify(authorService).updateById(1L, authorRequestDTO);
    }

    @Test
    void ShouldDeleteAuthor() {
        doNothing().when(authorService).deleteById(1L);

        ResponseEntity<Void> response = authorController.deleteAuthor(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorService).deleteById(1L);
    }

    @Test
    void ShouldGetAllAuthors() {
        List<AuthorResponseDTO> authors = new ArrayList<>();
        authors.add(authorResponseDTO);
        when(authorService.getAllAuthors()).thenReturn(authors);

        ResponseEntity<List<AuthorResponseDTO>> response = authorController.getAllAuthors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authors, response.getBody());
        verify(authorService).getAllAuthors();
    }
}
