package br.com.fiap.pettech.exception.controller;

import br.com.fiap.pettech.exception.service.DefaultError;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoForm extends DefaultError {

    private List<ValidacaoCampo> mensagens = new ArrayList<ValidacaoCampo>();

    public List<ValidacaoCampo> getMensagens() {
        return mensagens;
    }

    public void  addMenssagens(String campo, String mensagem) {
        mensagens.add(new ValidacaoCampo(campo, mensagem));
    }

}
