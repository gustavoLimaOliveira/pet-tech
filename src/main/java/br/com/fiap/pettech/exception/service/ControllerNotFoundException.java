package br.com.fiap.pettech.exception.service;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message){
        super(message);
    }
}
