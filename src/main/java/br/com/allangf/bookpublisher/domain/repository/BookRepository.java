package br.com.allangf.bookpublisher.domain.repository;

import br.com.allangf.bookpublisher.domain.entity.dao.BookDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDAO, Long> {
}