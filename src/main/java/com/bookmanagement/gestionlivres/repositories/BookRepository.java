package com.bookmanagement.gestionlivres.repositories;

import com.bookmanagement.gestionlivres.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("select b from  Book b where b.name like ?1%")
    Iterable<Book> findBookByName(String nomLivre);

    @Query("select b from Book b where b.price=?1")
    Iterable<Book>findBooksByPrice(@Param(("price"))Double price);


}
