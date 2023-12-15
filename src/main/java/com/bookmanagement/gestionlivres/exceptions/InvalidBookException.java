package com.bookmanagement.gestionlivres.exceptions;

public class InvalidBookException extends Throwable {
    public InvalidBookException(String ceLivreEstInvalide) {
        super(ceLivreEstInvalide);
    }
}
