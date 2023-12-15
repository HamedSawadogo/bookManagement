package com.bookmanagement.gestionlivres.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.bookmanagement.gestionlivres.exceptions.InvalidBookException;
import com.bookmanagement.gestionlivres.model.Book;
import com.bookmanagement.gestionlivres.service.BookServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookServiceImpl bookService;

    public  BookController(BookServiceImpl bookService){
        this.bookService=bookService;
    }

    /**
     * Rechercher les livres par Prix
     * @param price
     * @return
     */
    @GetMapping("/book/price/{price}")
    public ResponseEntity<?> findAllsBooksByPrice(@PathVariable("price")Double price){
      return  ResponseEntity
              .status(HttpStatus.OK)
              .body(this.bookService.findBooksByprice(price));
    }
    /**
     * Rechercher Un Livre par son Identifiant (ID)
     * @param bookId
     * @return
     */
    @GetMapping("/book/{id}")
    public  ResponseEntity findBookById(@PathVariable("id")Long bookId){
        Optional<Book>optionalBook=this.bookService.findBookById(bookId);
        if(optionalBook.isPresent()){
            return  ResponseEntity.ok().body(optionalBook.get());
        }
        return  ResponseEntity.notFound().build();
    }
    /**
     * Supprimer un Livre dans la base de donnée
     * @param bookid
     * @return
     */
    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id")Long bookid){
      try {
          this.bookService.deleteBook(bookid);
        return   ResponseEntity
                  .status(HttpStatus.OK)
                  .body("Livre supprimé avec success");
      }catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body("Impossible de supprimer ce produit");
      }
    }
    @GetMapping("/books")
    public List<Book> getBooks(){
        return  this.bookService.getAllBooks();
    }
    @GetMapping("/api/book/{id}")
    public ResponseEntity<?>getBook(@PathVariable("id")Long id){
        Optional<Book>optionalBook=this.bookService.findBookById(id);
        if(optionalBook.isPresent()){
            return  ResponseEntity.status(HttpStatus.OK).body(optionalBook.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ce Livre est inexistant");
    }
    @PostMapping("/book")
    public ResponseEntity<?>saveBook(@RequestBody Book book){
       if(Objects.isNull(book)){
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossible d'enregistrer ce Livre");
       }
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(this.bookService.addBook(book));
        } catch (InvalidBookException | Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Une erreur est survenue veillez verifier la vilidité de ce livre");
        }
    }
}
