package com.mferreira.coopapi.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    private String mensagem;

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
        return this.setMensagem(this.sessionClosed);
    }

    public ErrorMessage votacaoInvalida() {
        return this.setMensagem(this.votacaoInvalida);
    }

    public ErrorMessage javotou() {
        return this.setMensagem(this.jaVotou);
    }

    public ErrorMessage sessionInexistent() {
        return this.setMensagem(this.sessionInexistent);
    }

    public ErrorMessage cannotVote() {
        return this.setMensagem(this.cannotVote);
    }

    public ErrorMessage cpfInvalido() {
        return this.setMensagem(this.cpfInvalido);
    }

    public ErrorMessage pautaInvalida() {
        return this.setMensagem(this.pautaInvalida);
    }
}