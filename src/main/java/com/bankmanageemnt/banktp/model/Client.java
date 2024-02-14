package com.bankmanageemnt.banktp.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;


@Data @ToString
@NoArgsConstructor @AllArgsConstructor
@Table
@Entity
public class Client {
    @Id
    private String code;
    private String name;


    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "client")
    private List<Compte>comptes;
}
