package br.com.allangf.bookpublisher.mapper;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {

    @Test
    void ShouldMapAuthorDAOToAuthorResponseDTOWithoutBooks() {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.setId(1L);
        authorDAO.setName("Allan");

        AuthorResponseDTO responseDTO = AuthorMapper.daoToResponseDto(authorDAO);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Allan", responseDTO.getName());
    }

    @Test
    void ShouldMapAuthorDAOToAuthorResponseDTO() {
        BookDAO book1 = new BookDAO();
        book1.setId(1L);
        book1.setTitle("Book One");
        book1.setDescription("Description One");

        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.setId(1L);
        authorDAO.setName("Allan");
        authorDAO.setBooks(List.of(book1));

        AuthorResponseDTO responseDTO = AuthorMapper.daoToResponseDto(authorDAO);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Allan", responseDTO.getName());
        assertEquals("Book One", responseDTO.getBooks().get(0).getTitle());
    }
}