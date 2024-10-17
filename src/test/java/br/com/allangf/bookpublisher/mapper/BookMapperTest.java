package br.com.allangf.bookpublisher.mapper;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BookMapperTest {

    @Test
    void ShouldMapBookDAOToBookResponseDTOWithoutAuthor() {
        BookDAO bookDAO = new BookDAO();
        bookDAO.setId(1L);
        bookDAO.setTitle("Title");
        bookDAO.setDescription("Description");

        BookResponseDTO responseDTO = BookMapper.daoToResponseDto(bookDAO);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Title", responseDTO.getTitle());
        assertEquals("Description", responseDTO.getDescription());
        assertNull(responseDTO.getAuthor());
    }

    @Test
    void ShouldMapBookDAOToBookResponseDTOWithAuthor() {
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.setId(1L);
        authorDAO.setName("Allan");

        BookDAO bookDAO = new BookDAO();
        bookDAO.setId(1L);
        bookDAO.setTitle("Title");
        bookDAO.setDescription("Description");
        bookDAO.setAuthor(authorDAO);

        BookResponseDTO responseDTO = BookMapper.daoToResponseDto(bookDAO);

        assertEquals(1L, responseDTO.getId());
        assertEquals("Title", responseDTO.getTitle());
        assertEquals("Description", responseDTO.getDescription());
        assertEquals(1L, responseDTO.getAuthor().getId());
        assertEquals("Allan", responseDTO.getAuthor().getName());
    }
}