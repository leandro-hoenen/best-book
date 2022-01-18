package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {
}