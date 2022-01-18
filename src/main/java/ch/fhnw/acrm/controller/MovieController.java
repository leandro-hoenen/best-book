package ch.fhnw.acrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/movie")
public class MovieController {

    @GetMapping
    public String getMovieView(){
        return "acrm/movie.html";
    }

    @GetMapping("/create")
    public String getMovieCreateView(){
        return "../acrm/movieCreate.html";
    }

    @GetMapping("/edit")
    public String getMovieEditView(){
        return "../acrm/movieEdit.html";
    }
}
