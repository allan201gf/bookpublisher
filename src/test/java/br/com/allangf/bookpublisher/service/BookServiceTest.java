package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import br.com.allangf.bookpublisher.domain.repository.BookRepository;
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

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private BookRequestDTO bookRequestDTO;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorDAO = AuthorDAO.builder().id(1L).name("Allan").build();

        bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Test Book");
        bookRequestDTO.setDescription("Test Description");
        bookRequestDTO.setAuthorId(1L);

        bookDAO = new BookDAO();
        bookDAO.setId(1L);
        bookDAO.setTitle("Test Book");
        bookDAO.setDescription("Test Description");
        bookDAO.setAuthor(authorDAO);
    }

    @Test
    void ShouldCreateBook() {
        when(bookRepository.findByTitle(any())).thenReturn(Optional.empty());
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorDAO));
        when(bookRepository.save(any(BookDAO.class))).thenReturn(bookDAO);

        BookResponseDTO response = bookService.createBook(bookRequestDTO);

        assertEquals("Test Book", response.getTitle());
        verify(bookRepository).save(any(BookDAO.class));
    }

    @Test
    void ShouldThrowExceptionWhenBookTitleAlreadyExists() {
        when(bookRepository.findByTitle(any())).thenReturn(Optional.of(bookDAO));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.createBook(bookRequestDTO);
        });

        assertEquals("Book title already registered", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void ShouldThrowExceptionWhenAuthorIdNotFound() {
        when(bookRepository.findByTitle(any())).thenReturn(Optional.empty());
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            bookService.createBook(bookRequestDTO);
        });

        assertEquals("Author id not found", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    void ShouldReturnAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(bookDAO));

        List<BookResponseDTO> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    void ShouldReturnBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookDAO));

        BookResponseDTO response = bookService.getBookById(1L);

        assertEquals("Test Book", response.getTitle());
    }

    @Test
    void ShouldThrowExceptionWhenBookIdNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.getBookById(1L);
        });

        assertEquals("Book id not found", exception.getMessage());
    }

    @Test
    void ShouldReturnBookByTitle() {
        when(bookRepository.findByTitle("Test Book")).thenReturn(Optional.of(bookDAO));

        BookResponseDTO response = bookService.getBookByTitle("Test Book");

        assertEquals("Test Book", response.getTitle());
    }

    @Test
    void ShouldThrowExceptionWhenBookTitleNotFound() {
        when(bookRepository.findByTitle("test")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.getBookByTitle("test");
        });

        assertEquals("Book id not found", exception.getMessage());
    }

    @Test
    void ShouldUpdateBook() {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.setId(1L);
        authorDAO.setName("Author Name");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorDAO));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookDAO));
        when(bookRepository.save(any(BookDAO.class))).thenAnswer(invocation -> {
            BookDAO savedBook = invocation.getArgument(0);
            savedBook.setId(1L);
            return savedBook;
        });
        BookRequestDTO updatedRequest = new BookRequestDTO();
        updatedRequest.setTitle("Updated Book");
        updatedRequest.setDescription("Updated Description");
        updatedRequest.setAuthorId(1L);
        BookResponseDTO response = bookService.updateBook(1L, updatedRequest);
        assertEquals("Updated Book", response.getTitle());
        assertEquals("Updated Description", response.getDescription());
        verify(bookRepository).save(any(BookDAO.class));
    }

    @Test
    void ShouldThrowExceptionWhenBookIdNotFoundForUpdate() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.updateBook(1L, bookRequestDTO);
        });

        assertEquals("Book id not found", exception.getMessage());
    }

    @Test
    void ShouldDeleteBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookDAO));

        assertDoesNotThrow(() -> bookService.deleteBook(1L));
        verify(bookRepository).delete(bookDAO);
    }

    @Test
    void ShouldThrowExceptionWhenBookIdNotFoundForDelete() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            bookService.deleteBook(1L);
        });

        assertEquals("Book id not found", exception.getMessage());
    }
}