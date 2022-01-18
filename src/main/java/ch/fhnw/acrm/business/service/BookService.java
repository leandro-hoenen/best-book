package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.fhnw.acrm.data.domain.Book;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AgentService agentService;

    public Book enterBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> myBooks(){
        return bookRepository.findAll();
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Book editBook(@Valid Book book) {
        book.setAgent(agentService.getCurrentAgent());
        return bookRepository.save(book);
    }

    public Book findBookById(Long bookId) throws Exception {
        List<Book> bookList = bookRepository.findByIdAndAgentId(bookId, agentService.getCurrentAgent().getId());
        if(bookList.isEmpty()){
            throw new Exception("No book with ID "+bookId+" found.");
        }
        return bookList.get(0);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findByAgentId(agentService.getCurrentAgent().getId());
    }

}
