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
public class CustomerEndpoint {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;
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

    //added for books
    @PostMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        try {
            book = bookService.enterBusinessBook(book);
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

    @GetMapping(path = "/customer", produces = "application/json")
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }

    //added for books
    @GetMapping(path = "/book", produces = "application/json")
    public List<Book> getBook(){
        return bookService.myBooks();
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