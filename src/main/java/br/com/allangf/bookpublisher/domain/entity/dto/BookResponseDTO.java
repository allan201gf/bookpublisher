package br.com.allangf.bookpublisher.domain.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDTO {
    private Long id;
    private String title;
    private String description;
    private AuthorResponseDTO author;
}