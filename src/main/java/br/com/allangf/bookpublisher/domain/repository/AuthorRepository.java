package br.com.allangf.bookpublisher.domain.repository;

import br.com.allangf.bookpublisher.domain.entity.AuthorDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorDAO, Long> {
}