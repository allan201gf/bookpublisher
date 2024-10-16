package br.com.allangf.bookpublisher.controller;

import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/v1")
@AllArgsConstructor
@Tag(name = "Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookDAO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookDAO createdBook = bookService.createBook(bookRequestDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find book by id")
    public ResponseEntity<BookDAO> getBookById(@PathVariable Long id) {
        BookDAO book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Find all books")
    public ResponseEntity<List<BookDAO>> getAllBooks() {
        List<BookDAO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book")
    public ResponseEntity<BookDAO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        BookDAO updatedBook = bookService.updateBook(id, bookRequestDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book by id")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}