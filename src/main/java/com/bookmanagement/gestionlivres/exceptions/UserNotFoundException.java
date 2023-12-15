package com.bookmanagement.gestionlivres.exceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String ceUtilisateurEstInvalide) {
        super(ceUtilisateurEstInvalide);
    }
}
