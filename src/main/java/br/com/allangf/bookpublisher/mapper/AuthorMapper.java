package br.com.allangf.bookpublisher.mapper;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.BookResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorResponseDTO daoToResponseDto(AuthorDAO authorDAO) {
        AuthorResponseDTO responseDTO = new AuthorResponseDTO();
        responseDTO.setId(authorDAO.getId());
        responseDTO.setName(authorDAO.getName());

        if (authorDAO.getBooks() != null) {
            List<BookResponseDTO> bookResponseList = authorDAO.getBooks().stream().map(book -> {
                BookResponseDTO bookResponse = new BookResponseDTO();
                bookResponse.setId(book.getId());
                bookResponse.setTitle(book.getTitle());
                bookResponse.setDescription(book.getDescription());
                return bookResponse;
            }).collect(Collectors.toList());
            responseDTO.setBooks(bookResponseList);
        }

        return responseDTO;
    }

}
