package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.exceptions.InvalidBookException;
import com.bookmanagement.gestionlivres.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    /*
    *Ajouter un Livre dans la base de donnée
     */
    Book addBook(Book book) throws InvalidBookException;

    /**
     * Supprimer un Livre de la base de donnée
     * @param bookId
     */
    void deleteBook(Long bookId);
    /**
     * Renvoie la liste des livres de la base de donné
     * @return
     */
    List<Book>getAllBooks();

    /**
     * Rechercher un livre par son Id dans la bas de donnée
     * @param bookid
     * @return
     */
    Optional<Book>findBookById(Long bookid);
    /*
    Mettre a jour un Livre de la base de donnée
     */
    void upDateBook(Book book,Long bookId);
    /**
     * Recher les livres par prix
     * @param price
     * @return
     */
    Iterable<Book>findBooksByprice(Double price);
}
