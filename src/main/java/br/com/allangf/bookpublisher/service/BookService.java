package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import br.com.allangf.bookpublisher.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookDAO createBook(BookRequestDTO bookRequestDTO) {
        AuthorDAO author = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        BookDAO book = new BookDAO();
        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public List<BookDAO> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDAO getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));
    }

    public BookDAO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        BookDAO book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());

        if (bookRequestDTO.getAuthorId() != null) {
            AuthorDAO author = authorRepository.findById(bookRequestDTO.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("tratar erro"));
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        BookDAO book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        bookRepository.delete(book);
    }
}