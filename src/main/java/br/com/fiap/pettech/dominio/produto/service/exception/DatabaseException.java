package br.com.fiap.pettech.dominio.produto.service.exception;

public class DatabaseException extends  RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
