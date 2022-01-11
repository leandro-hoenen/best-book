package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}