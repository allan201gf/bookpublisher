package br.com.allangf.bookpublisher.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDTO {
    private String title;
    private String description;
    private Long authorId;
}