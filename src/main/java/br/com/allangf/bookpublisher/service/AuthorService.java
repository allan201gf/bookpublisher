package br.com.allangf.bookpublisher.service;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorRequestDTO;
import br.com.allangf.bookpublisher.domain.entity.dto.AuthorResponseDTO;
import br.com.allangf.bookpublisher.domain.repository.AuthorRepository;
import br.com.allangf.bookpublisher.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        if (repository.findByName(authorRequestDTO.getNome()).isPresent()) {
            throw new RuntimeException("tratar erro");
        }

        AuthorDAO authorDAO = AuthorDAO.builder().name(authorRequestDTO.getNome()).books(new ArrayList<>()).build();
        repository.save(authorDAO);

        return AuthorMapper.daoToResponseDto(authorDAO);
    }

    public AuthorResponseDTO findById(Long id) {
        AuthorDAO authorDAO = repository.findById(id).orElseThrow(() -> new RuntimeException("tratar erro"));
        return AuthorMapper.daoToResponseDto(authorDAO);
    }

    public AuthorResponseDTO findByName(String name) {
        AuthorDAO authorDAO = repository.findByName(name).orElseThrow(() -> new RuntimeException("tratar erro"));
        return AuthorMapper.daoToResponseDto(authorDAO);
    }

    public AuthorResponseDTO updateById(Long id, AuthorRequestDTO authorRequestDTO) {
        AuthorDAO author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        author.setName(authorRequestDTO.getNome());

        return AuthorMapper.daoToResponseDto(repository.save(author));
    }

    public void deleteById(Long id) {
        AuthorDAO author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("tratar erro"));

        repository.delete(author);
    }

    public List<AuthorResponseDTO> getAllAuthors() {
        List<AuthorDAO> daoList = repository.findAll();
        List<AuthorResponseDTO> responseDTO = new ArrayList<>();
        daoList.forEach(authorDAO -> responseDTO.add(AuthorMapper.daoToResponseDto(authorDAO)));
        return responseDTO;
    }
}
