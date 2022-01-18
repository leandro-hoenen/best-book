package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.domain.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
    List<VideoGame> findByAgentId(Long agentId);
    List<VideoGame> findByIdAndAgentId(Long videoGameId, Long agentId);
}