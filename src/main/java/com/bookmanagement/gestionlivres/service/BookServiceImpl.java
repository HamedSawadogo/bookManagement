package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.exceptions.InvalidBookException;
import com.bookmanagement.gestionlivres.model.Book;
import com.bookmanagement.gestionlivres.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public  BookServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    @Override
    public Book addBook(Book book) throws InvalidBookException {
        if(book.getQuantite()<=0){
            throw  new InvalidBookException("Ce livre est invalide");
        }
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long bookid) {
        return this.bookRepository.findById(bookid);
    }
    @Override
    public void upDateBook(Book book, Long bookId) {

    }
    @Override
    public Iterable<Book> findBooksByprice(Double price) {
        return this.bookRepository.findBooksByPrice(price);
    }
}
