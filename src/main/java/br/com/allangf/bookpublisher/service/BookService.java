package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import br.com.allangf.bookpublisher.domain.repository.BookRepository;
import br.com.allangf.bookpublisher.mapper.BookMapper;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        log.info("Checking if the book already exists...");
        if (repository.findByTitle(bookRequestDTO.getTitle()).isPresent()) {
            throw new BadRequestException("Book title already registered");
        }

        log.info("Checking if the author already exists...");
        AuthorDAO author = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new BadRequestException("Author id not found"));

        BookDAO book = new BookDAO();
        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());
        book.setAuthor(author);

        log.info("Saving book in database...");
        return BookMapper.daoToResponseDto(repository.save(book));
    }

    public List<BookResponseDTO> getAllBooks() {
        log.info("Searching for all books in the database");
        List<BookDAO> daoList = repository.findAll();
        List<BookResponseDTO> responseDTO = new ArrayList<>();
        daoList.forEach(bookDAO -> responseDTO.add(BookMapper.daoToResponseDto(bookDAO)));
        return responseDTO;
    }

    public BookResponseDTO getBookById(Long id) {
        BookDAO bookDAO = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id not found"));
        return BookMapper.daoToResponseDto(bookDAO);
    }

    public BookResponseDTO getBookByTitle(String title) {
        BookDAO bookDAO = repository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Book id not found"));
        return BookMapper.daoToResponseDto(bookDAO);
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        log.info("Searching book by id...");
        BookDAO book = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id not found"));

        log.info("Updating book...");
        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());

        log.info("Checking if the author already exists...");
        if (bookRequestDTO.getAuthorId() != null) {
            AuthorDAO author = authorRepository.findById(bookRequestDTO.getAuthorId())
                    .orElseThrow(() -> new NotFoundException("Author id not found"));
            book.setAuthor(author);
        }

        return BookMapper.daoToResponseDto(repository.save(book));
    }

    public void deleteBook(Long id) {
        BookDAO book = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book id not found"));

        repository.delete(book);
    }
}