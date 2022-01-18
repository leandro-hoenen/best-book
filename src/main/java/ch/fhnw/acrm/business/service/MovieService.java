package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Book;
import ch.fhnw.acrm.data.domain.Movie;
import ch.fhnw.acrm.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private AgentService agentService;

    public Movie enterMovie(@Valid Movie movie) throws Exception {
        if (movie.getId() == null) {
            movie.setAgent(agentService.getCurrentAgent());
            return movieRepository.save(movie);
        }
        throw new Exception("Movie object already exists");
    }

    public List<Movie> myMovies(){
        return movieRepository.findByAgentId(agentService.getCurrentAgent().getId());
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
