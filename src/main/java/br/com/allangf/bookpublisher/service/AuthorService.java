package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public AuthorDAO createAuthor(AuthorRequestDTO authorRequestDTO) {

        if (repository.findByName(authorRequestDTO.getNome()).isPresent()) {
            throw new RuntimeException("tratar erro");
        }

        AuthorDAO authorDAO = AuthorDAO.builder().name(authorRequestDTO.getNome()).books(new ArrayList<>()).build();
        repository.save(authorDAO);
        return authorDAO;
    }

    public AuthorDAO findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("tratar erro"));
    }

    public AuthorDAO findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new RuntimeException("tratar erro"));
    }

    public AuthorDAO updateById(Long id, AuthorRequestDTO authorRequestDTO) {
        AuthorDAO author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        author.setName(authorRequestDTO.getNome());

        return repository.save(author);
    }

    public void deleteById(Long id) {
        AuthorDAO author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        repository.delete(author);
    }

    public List<AuthorDAO> getAllAuthors() {
        return repository.findAll();
    }
}
