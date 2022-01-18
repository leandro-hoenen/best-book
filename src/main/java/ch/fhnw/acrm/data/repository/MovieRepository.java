package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {


}