/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.api;

import ch.fhnw.acrm.business.service.BookService;
import ch.fhnw.acrm.business.service.VideoGameService;
import ch.fhnw.acrm.data.domain.Book;
import ch.fhnw.acrm.data.domain.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.fhnw.acrm.business.service.CustomerService;
import ch.fhnw.acrm.data.domain.Customer;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class VideoGameEndpoint {
    @Autowired
    private VideoGameService videoGameService;

    @PostMapping(path = "/videogame/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<VideoGame> postVideoGame(@RequestBody VideoGame videoGame) {
        try {
            videoGame = videoGameService.addVideoGame(videoGame);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{videoGameId}")
                .buildAndExpand(videoGame.getId()).toUri();

        return ResponseEntity.created(location).body(videoGame);
    }

    @GetMapping(path = "/videogame", produces = "application/json")
    public List<VideoGame> getVideoGame() {
        return videoGameService.getMyVideoGames();
    }
}
