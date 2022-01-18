package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Movie;
import ch.fhnw.acrm.data.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie enterMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> myMovies(){
        return movieRepository.findAll();
    }

    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
