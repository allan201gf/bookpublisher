package br.com.allangf.bookpublisher.domain.repository;

import br.com.allangf.bookpublisher.domain.entity.dao.AuthorDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorDAO, Long> {

    Optional<AuthorDAO> findByName(String name);

}