package com.bankmanageemnt.banktp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data @ToString
@NoArgsConstructor @AllArgsConstructor
@Table
@Entity
@Builder
public class Client implements UserDetails {
    @Id
    private String code;
    @NotBlank(message = "Le nom du Client est invalide!! ")
    @Column(unique = true)
    private String name;

    private String password;
    private String email;


    @ManyToOne(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "client",
    orphanRemoval = true)
    private List<Compte>comptes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
