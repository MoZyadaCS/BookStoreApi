package com.bookstore.bookstoreapi.exception;

public class NoAuthorFoundException extends RuntimeException{

    public NoAuthorFoundException(String message){
        super(message);
    }
}
