package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Book;
import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.domain.Movie;
import ch.fhnw.acrm.data.domain.VideoGame;
import ch.fhnw.acrm.data.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private AgentService agentService;

    public VideoGame addVideoGame(@Valid VideoGame videoGame) throws Exception {
        if (videoGame.getId() == null) {
            videoGame.setAgent(agentService.getCurrentAgent());
            return videoGameRepository.save(videoGame);
        }
        throw new Exception("VideoGame object already exists");
    }

    public List<VideoGame> getMyVideoGames() {
        return videoGameRepository.findByAgentId(agentService.getCurrentAgent().getId());
    }

    public void deleteVideoGame(Long videoGameId) {
        videoGameRepository.deleteById(videoGameId);
    }

    public VideoGame editVideoGame(@Valid VideoGame videoGame) throws Exception {
        if (videoGameRepository.findById(videoGame.getId()) != null) {
            videoGame.setAgent(agentService.getCurrentAgent());
            return videoGameRepository.save(videoGame);
        }
        throw new Exception("Book object does not exists");
    }

    // TODO: implement edit videoGame
}
