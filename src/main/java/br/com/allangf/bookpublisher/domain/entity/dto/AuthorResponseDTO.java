package br.com.allangf.bookpublisher.domain.entity.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private List<BookResponseDTO> books;
}