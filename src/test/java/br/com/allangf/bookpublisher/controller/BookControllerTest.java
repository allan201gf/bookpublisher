package br.com.allangf.bookpublisher.controller;

import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;
import br.com.allangf.bookpublisher.service.BookService;
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

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookRequestDTO bookRequestDTO;
    private BookResponseDTO bookResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Book Title");
        bookRequestDTO.setDescription("Book Description");

        bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(1L);
        bookResponseDTO.setTitle("Book Title");
        bookResponseDTO.setDescription("Book Description");
    }

    @Test
    void ShouldCreateBook() {
        when(bookService.createBook(any(BookRequestDTO.class))).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.createBook(bookRequestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(bookService).createBook(bookRequestDTO);
    }

    @Test
    void ShouldGetBookById() {
        when(bookService.getBookById(1L)).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.getBookById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(bookService).getBookById(1L);
    }

    @Test
    void ShouldGetBookByTitle() {
        when(bookService.getBookByTitle("Book Title")).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.getBookByTitle("Book Title");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(bookService).getBookByTitle("Book Title");
    }

    @Test
    void ShouldGetAllBooks() {
        List<BookResponseDTO> books = new ArrayList<>();
        books.add(bookResponseDTO);
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<BookResponseDTO>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(bookService).getAllBooks();
    }

    @Test
    void ShouldUpdateBook() {
        when(bookService.updateBook(1L, bookRequestDTO)).thenReturn(bookResponseDTO);

        ResponseEntity<BookResponseDTO> response = bookController.updateBook(1L, bookRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookResponseDTO, response.getBody());
        verify(bookService).updateBook(1L, bookRequestDTO);
    }

    @Test
    void ShouldDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService).deleteBook(1L);
    }
}
