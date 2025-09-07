package com.example.Contact_Manager_Application.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message){
        super(message);
    }
}
