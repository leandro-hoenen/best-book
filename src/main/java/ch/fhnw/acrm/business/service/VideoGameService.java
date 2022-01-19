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

    public VideoGame findVideoGameById(Long videogameId) throws Exception {
        List<VideoGame> videoGameList = videoGameRepository.findByIdAndAgentId(videogameId, agentService.getCurrentAgent().getId());
        if(videoGameList.isEmpty()){
            throw new Exception("No movie with ID "+videogameId+" found.");
        }
        return videoGameList.get(0);
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
