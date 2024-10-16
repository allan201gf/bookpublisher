package br.com.allangf.bookpublisher.domain.repository;

import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookDAO, Long> {

    Optional<BookDAO> findByTitle(String title);

}