package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import br.com.allangf.bookpublisher.mapper.AuthorMapper;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        log.info("Checking if the author already exists...");
        if (repository.findByName(authorRequestDTO.getName()).isPresent()) {
            throw new BadRequestException("Author name already registered");
        }

        log.info("Saving author in database...");
        AuthorDAO authorDAO = AuthorDAO.builder().name(authorRequestDTO.getName()).books(new ArrayList<>()).build();
        repository.save(authorDAO);
        return AuthorMapper.daoToResponseDto(authorDAO);
    }

    public AuthorResponseDTO findById(Long id) {
        AuthorDAO authorDAO = repository.findById(id).orElseThrow(() -> new NotFoundException("Author id not found"));
        return AuthorMapper.daoToResponseDto(authorDAO);
    }

    public AuthorResponseDTO findByName(String name) {
        AuthorDAO authorDAO = repository.findByName(name).orElseThrow(() -> new NotFoundException("Author id not found"));
        return AuthorMapper.daoToResponseDto(authorDAO);
    }

    public AuthorResponseDTO updateById(Long id, AuthorRequestDTO authorRequestDTO) {
        log.info("Searching author by id...");
        AuthorDAO author = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author id not found"));

        log.info("Updating author name: {}", authorRequestDTO.getName());
        author.setName(authorRequestDTO.getName());

        return AuthorMapper.daoToResponseDto(repository.save(author));
    }

    public void deleteById(Long id) {
        log.info("Checking if the author exists in the database...");
        AuthorDAO author = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author id not found"));

        repository.delete(author);
        log.info("Author deleted successfully");
    }

    public List<AuthorResponseDTO> getAllAuthors() {
        log.info("Searching for all authors in the database");
        List<AuthorDAO> daoList = repository.findAll();
        List<AuthorResponseDTO> responseDTO = new ArrayList<>();
        daoList.forEach(authorDAO -> responseDTO.add(AuthorMapper.daoToResponseDto(authorDAO)));
        return responseDTO;
    }
}
