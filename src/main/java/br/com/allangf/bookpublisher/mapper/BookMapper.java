package br.com.allangf.bookpublisher.mapper;

import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;

public class BookMapper {

    public static BookResponseDTO daoToResponseDto(BookDAO bookDAO) {
        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(bookDAO.getId());
        responseDTO.setTitle(bookDAO.getTitle());
        responseDTO.setDescription(bookDAO.getDescription());

        if (bookDAO.getAuthor() != null) {
            AuthorResponseDTO authorResponse = new AuthorResponseDTO();
            authorResponse.setId(bookDAO.getAuthor().getId());
            authorResponse.setNome(bookDAO.getAuthor().getName());
            responseDTO.setAuthor(authorResponse);
        }

        return responseDTO;
    }

}
