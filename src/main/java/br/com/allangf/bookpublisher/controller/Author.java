package br.com.allangf.bookpublisher.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/author/v1")
@AllArgsConstructor
public class Author {


    @GetMapping()
    public String test() {
        return LocalDateTime.now().toString();
    }

}
