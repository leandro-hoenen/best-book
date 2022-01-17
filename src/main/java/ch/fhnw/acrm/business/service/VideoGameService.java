package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.VideoGame;
import ch.fhnw.acrm.data.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameService {
    @Autowired
    private VideoGameRepository videoGameRepository;

    public VideoGame addVideoGame(VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }

    public List<VideoGame> getMyVideoGames() {
        return videoGameRepository.findAll();
    }

    // TODO: implement delete and edit videoGame
}
