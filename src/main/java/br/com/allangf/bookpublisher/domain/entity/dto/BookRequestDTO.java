package br.com.allangf.bookpublisher.domain.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookRequestDTO {
    private String title;
    private String description;
    private Long authorId;
}