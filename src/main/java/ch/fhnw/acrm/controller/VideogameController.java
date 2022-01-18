package ch.fhnw.acrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/videogame")
public class VideogameController {

    @GetMapping
    public String getVideogameView(){
        return "acrm/videogame.html";
    }

    @GetMapping("/create")
    public String getVideogameCreateView(){
        return "../acrm/videogameCreate.html";
    }

    @GetMapping("/edit")
    public String getVideogameEditView(){
        return "../acrm/videogameEdit.html";
    }
}
