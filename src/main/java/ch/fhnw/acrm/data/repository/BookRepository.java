package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.Book;
import ch.fhnw.acrm.data.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAgentId(Long agentId);
    List<Book> findByIdAndAgentId(Long bookId, Long agentId);
}