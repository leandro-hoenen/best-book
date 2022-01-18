package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ch.fhnw.acrm.data.domain.Book;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.acrm.data.domain.Agent;
import ch.fhnw.acrm.data.repository.AgentRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AgentService agentService;

    public Book enterBusinessBook(@Valid Book book) throws Exception {
        if (book.getId() == null) {
            book.setAgent(agentService.getCurrentAgent());
            return bookRepository.save(book);
        }
        throw new Exception("Book object already exists");
    }

    public List<Book> myBooks(){
        return bookRepository.findAll();
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
