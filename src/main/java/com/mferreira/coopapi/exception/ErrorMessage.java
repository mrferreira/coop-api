package com.mferreira.coopapi.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    private String mensagem;

    @Value("${msg.session.closed}")
    protected String sessionClosed;
    @Value("${msg.votacao.invalida}")
    protected String votacaoInvalida;
    @Value("${msg.ja.votou}")
    protected String jaVotou;

    public String getMensagem() {
        return mensagem;
    }

    public String json() {
        return String.format("{\"mensagem\": \"%s\"}", mensagem);
    }

    public ErrorMessage setMensagem(String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public ErrorMessage sessionClosed() {
        return this.setMensagem(this.sessionClosed);
    }

    public ErrorMessage votacaoInvalida() {
        return this.setMensagem(this.votacaoInvalida);
    }

    public ErrorMessage javotou() {
        return this.setMensagem(this.jaVotou);
    }
}