package br.com.allangf.bookpublisher.controller;

import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;
import br.com.allangf.bookpublisher.service.BookService;
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
@RequestMapping("/book/v1")
@AllArgsConstructor
@Tag(name = "Books", description = "Book operations")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        log.info("New request received at createBook, body: {}", bookRequestDTO.toString());
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);
        log.info("Response at createBook: {}", createdBook);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find book by id")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        log.info("New request received at getBookById, id: {}", id);
        BookResponseDTO book = bookService.getBookById(id);
        log.info("Response at getBookById: {}", book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Find book by title")
    public ResponseEntity<BookResponseDTO> getBookByTitle(@PathVariable String title) {
        log.info("New request received at getBookByTitle, title: {}", title);
        BookResponseDTO book = bookService.getBookByTitle(title);
        log.info("Response at getBookByTitle: {}", book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Find all books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        log.info("New request received at getAllBooks");
        List<BookResponseDTO> books = bookService.getAllBooks();
        log.info("Response at getAllBooks: {}", books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        log.info("New request received at updateBook - id: {}, body: {}", id, bookRequestDTO);
        BookResponseDTO updatedBook = bookService.updateBook(id, bookRequestDTO);
        log.info("Response at updateBook: {}", updatedBook);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("New request received at deleteBook: {}", id);
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}