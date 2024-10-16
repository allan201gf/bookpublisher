package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import br.com.allangf.bookpublisher.domain.repository.BookRepository;
import br.com.allangf.bookpublisher.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        AuthorDAO author = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        BookDAO book = new BookDAO();
        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());
        book.setAuthor(author);

        return BookMapper.daoToResponseDto(bookRepository.save(book));
    }

    public List<BookResponseDTO> getAllBooks() {
        List<BookDAO> daoList = bookRepository.findAll();
        List<BookResponseDTO> responseDTO = new ArrayList<>();
        daoList.forEach(bookDAO -> responseDTO.add(BookMapper.daoToResponseDto(bookDAO)));
        return responseDTO;
    }

    public BookResponseDTO getBookById(Long id) {
        BookDAO bookDAO = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));
        return BookMapper.daoToResponseDto(bookDAO);
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        BookDAO book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());

        if (bookRequestDTO.getAuthorId() != null) {
            AuthorDAO author = authorRepository.findById(bookRequestDTO.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("tratar erro"));
            book.setAuthor(author);
        }

        return BookMapper.daoToResponseDto(bookRepository.save(book));
    }

    public void deleteBook(Long id) {
        BookDAO book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        bookRepository.delete(book);
    }
}