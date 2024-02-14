package com.bankmanageemnt.banktp.dao;
import com.bankmanageemnt.banktp.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface ClientDao extends JpaRepository<Client,String> {

    //rechercher les Clients Par Nom
    @Query("SELECT c FROM Client  c WHERE c.name LIKE  %:name%")
    List<Client>findClientsByName(@Param("name")String clientName);


    Page<Client> findAll(Pageable pageable);
}
