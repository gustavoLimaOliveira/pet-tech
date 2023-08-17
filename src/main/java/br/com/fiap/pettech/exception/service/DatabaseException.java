package br.com.fiap.pettech.exception.service;

public class DatabaseException extends  RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
