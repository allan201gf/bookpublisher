package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorRequestDTO authorRequestDTO;
    private AuthorDAO authorDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorRequestDTO = new AuthorRequestDTO();
        authorRequestDTO.setName("Allan");

        authorDAO = AuthorDAO.builder().id(1L).name("Allan").build();
    }

    @Test
    void ShouldCreateAuthor() {
        when(repository.findByName(any())).thenReturn(Optional.empty());
        when(repository.save(any(AuthorDAO.class))).thenReturn(authorDAO);

        AuthorResponseDTO response = authorService.createAuthor(authorRequestDTO);

        assertEquals("Allan", response.getName());
        verify(repository).save(any(AuthorDAO.class));
    }

    @Test
    void ShouldThrowExceptionWhenAuthorAlreadyExists() {
        when(repository.findByName(any())).thenReturn(Optional.of(authorDAO));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            authorService.createAuthor(authorRequestDTO);
        });

        assertEquals("Author name already registered", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void ShouldReturnAuthorWhenAuthorIdExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(authorDAO));

        AuthorResponseDTO response = authorService.findById(1L);

        assertEquals("Allan", response.getName());
    }

    @Test
    void ShouldThrowExceptionWhenAuthorDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            authorService.findById(1L);
        });

        assertEquals("Author id not found", exception.getMessage());
    }

    @Test
    void ShouldReturnUpdatedAuthor() {
        when(repository.findById(1L)).thenReturn(Optional.of(authorDAO));
        when(repository.save(any())).thenReturn(authorDAO);

        AuthorResponseDTO response = authorService.updateById(1L, authorRequestDTO);

        assertEquals("Allan", response.getName());
        verify(repository).save(authorDAO);
    }

    @Test
    void ShouldThrowExceptionWhenAuthorDoesNotExistUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            authorService.updateById(1L, authorRequestDTO);
        });

        assertEquals("Author id not found", exception.getMessage());
    }

    @Test
    void ShouldDeleteAuthor() {
        when(repository.findById(1L)).thenReturn(Optional.of(authorDAO));

        assertDoesNotThrow(() -> authorService.deleteById(1L));
        verify(repository).delete(authorDAO);
    }

    @Test
    void ShouldThrowExceptionWhenAuthorDoesNotExistDelete() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            authorService.deleteById(1L);
        });

        assertEquals("Author id not found", exception.getMessage());
    }

    @Test
    void ShouldReturnListOfAuthors() {
        when(repository.findAll()).thenReturn(List.of(authorDAO));

        List<AuthorResponseDTO> authors = authorService.getAllAuthors();

        assertEquals(1, authors.size());
        assertEquals("Allan", authors.get(0).getName());
    }

    @Test
    void ShouldReturnAuthorWhenAuthorNameExists() {
        when(repository.findByName("Allan")).thenReturn(Optional.of(authorDAO));

        AuthorResponseDTO response = authorService.findByName("Allan");

        assertEquals("Allan", response.getName());
    }

    @Test
    void ShouldThrowExceptionWhenAuthorNameDoesNotExist() {
        when(repository.findByName("test")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            authorService.findByName("test");
        });

        assertEquals("Author id not found", exception.getMessage());
    }
}