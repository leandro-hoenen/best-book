/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.api;

import ch.fhnw.acrm.business.service.BookService;
import ch.fhnw.acrm.business.service.MovieService;
import ch.fhnw.acrm.business.service.VideoGameService;
import ch.fhnw.acrm.data.domain.Book;
import ch.fhnw.acrm.data.domain.Movie;
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
public class CustomerEndpoint {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private VideoGameService videoGameService;

    // VIDEOGAME mappings
    @PostMapping(path = "/videogame", consumes = "application/json", produces = "application/json")
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

    @DeleteMapping(path = "/videogame/{videogameId}")
    public ResponseEntity<Void> deleteVideoGame(@PathVariable(value = "videogameId") String videoGameId) {
        try {
            videoGameService.deleteVideoGame(Long.parseLong(videoGameId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }

    // MOVIE mappings
    @GetMapping(path = "/movie", produces = "application/json")
    public List<Movie> getMovie(){
        return movieService.findAllMovies();
    }

    @PostMapping(path = "/movie", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Movie> postMovie(@RequestBody Movie movie) {
        try {
            movie = movieService.enterMovie(movie);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{movieId}")
                .buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(location).body(movie);
    }

    @PutMapping(path = "/movie/{movieId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Movie> putMovie(@RequestBody Movie movie, @PathVariable(value = "movieId") String movieId) {
        try {
            movie.setId(Long.parseLong(movieId));
            movie = movieService.editMovie(movie);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(movie);
    }

    @GetMapping(path = "/movie/{movieId}", produces = "application/json")
    public ResponseEntity<Movie> getMovie(@PathVariable(value = "movieId") String movieId) {
        Movie movie = null;
        try {
            movie = movieService.findMovieById(Long.parseLong(movieId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping(path = "/movie/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable(value = "movieId") String movieId) {
        try {
            movieService.deleteMovie(Long.parseLong(movieId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }

    // BOOKS mappings
    @GetMapping(path = "/book", produces = "application/json")
    public List<Book> getBooks(){
        return bookService.findAllBooks();
    }

    @PostMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        try {
            book = bookService.enterBook(book);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{bookId}")
                .buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(location).body(book);
    }

    @PutMapping(path = "/book/{bookId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> putBook(@RequestBody Book book, @PathVariable(value = "bookId") String bookId) {
        try {
            book.setId(Long.parseLong(bookId));
            book = bookService.editBook(book);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(book);
    }

    @GetMapping(path = "/book/{bookId}", produces = "application/json")
    public ResponseEntity<Book> getBook(@PathVariable(value = "bookId") String bookId) {
        Book book = null;
        try {
            book = bookService.findBookById(Long.parseLong(bookId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(book);
    }

    @DeleteMapping(path = "/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "bookId") String bookId) {
        try {
            bookService.deleteBook(Long.parseLong(bookId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }

    // CUSTOMER mappings
    @GetMapping(path = "/customer", produces = "application/json")
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }

    @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        try {
            customer = customerService.editCustomer(customer);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{customerId}")
                .buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(location).body(customer);
    }

    @GetMapping(path = "/customer/{customerId}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "customerId") String customerId) {
        Customer customer = null;
        try {
            customer = customerService.findCustomerById(Long.parseLong(customerId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping(path = "/customer/{customerId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> putCustomer(@RequestBody Customer customer, @PathVariable(value = "customerId") String customerId) {
        try {
            customer.setId(Long.parseLong(customerId));
            customer = customerService.editCustomer(customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(customer);
    }

    @DeleteMapping(path = "/customer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "customerId") String customerId) {
        try {
            customerService.deleteCustomer(Long.parseLong(customerId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
}