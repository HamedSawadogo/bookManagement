package com.bookmanagement.gestionlivres.model;

import com.bookmanagement.gestionlivres.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class User {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private String phone;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private List<Compte>comptes=new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
           cascade = CascadeType.ALL,
          mappedBy = "user"
    )
    private List<Reservation>reservations=new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
