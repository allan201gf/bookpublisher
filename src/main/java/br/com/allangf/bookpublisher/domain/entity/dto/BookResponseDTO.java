package br.com.allangf.bookpublisher.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDTO {
    private Long id;
    private String title;
    private String description;
    private AuthorResponseDTO author;
}