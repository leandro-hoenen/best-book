package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Book;
import ch.fhnw.acrm.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book enterBusinessBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> myBooks(){
        return bookRepository.findAll();
    }
}
