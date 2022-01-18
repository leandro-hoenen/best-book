package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByAgentId(Long agentId);
    List<Movie> findByIdAndAgentId(Long movieId, Long agentId);
}