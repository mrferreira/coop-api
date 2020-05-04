package com.mferreira.coopapi.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    private String mensagem;
    private HttpStatus status;

    @Value("${msg.session.closed}")
    protected String sessionClosed;
    @Value("${msg.session.inexistent}")
    protected String sessionInexistent;
    @Value("${msg.votacao.invalida}")
    protected String votacaoInvalida;
    @Value("${msg.ja.votou}")
    protected String jaVotou;
    @Value("${msg.cpf.cannot.vote}")
    protected String cannotVote;
    @Value("${msg.cpf.invalido}")
    protected String cpfInvalido;
    @Value("${msg.pauta.invalida}")
    private String pautaInvalida;

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
        return this.setMensagem(this.sessionClosed)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage votacaoInvalida() {
        return this.setMensagem(this.votacaoInvalida)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage javotou() {
        return this.setMensagem(this.jaVotou)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage sessionInexistent() {
        return this.setMensagem(this.sessionInexistent)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage cannotVote() {
        return this.setMensagem(this.cannotVote)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage cpfInvalido() {
        return this.setMensagem(this.cpfInvalido)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage pautaInvalida() {
        return this.setMensagem(this.pautaInvalida)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}