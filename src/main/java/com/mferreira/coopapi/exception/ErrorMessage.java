package com.mferreira.coopapi.exception;

import com.mferreira.coopapi.configuration.MessagesConfiguration;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    private String mensagem;
    private HttpStatus status;
    private MessagesConfiguration messagesConfiguration;
    protected final String sessionClosed;
    protected final String sessionInexistent;
    protected final String votacaoInvalida;
    protected final String sessaoInvalida;
    protected final String opcaoInvalida;
    protected final String jaVotou;
    protected final String cannotVote;
    protected final String cpfInvalido;
    protected final String pautaInvalida;
    protected final String pautaJaExisteComNome;
    protected final String insertException;
    protected final String searchException;

    public ErrorMessage(MessagesConfiguration messagesConfiguration) {
        this.messagesConfiguration = messagesConfiguration;
        this.sessionClosed = messagesConfiguration.get("msg.session.closed");
        this.sessionInexistent = messagesConfiguration.get("msg.session.inexistent");
        this.votacaoInvalida = messagesConfiguration.get("msg.votacao.invalida");
        this.sessaoInvalida = messagesConfiguration.get("msg.sessao.invalida");
        this.opcaoInvalida = messagesConfiguration.get("msg.opcao.invalida");
        this.jaVotou = messagesConfiguration.get("msg.ja.votou");
        this.cannotVote = messagesConfiguration.get("msg.cpf.cannot.vote");
        this.cpfInvalido = messagesConfiguration.get("msg.cpf.invalido");
        this.pautaInvalida = messagesConfiguration.get("msg.pauta.invalida");
        this.pautaJaExisteComNome = messagesConfiguration.get("msg.pauta.ja.existe.nome");
        this.insertException = messagesConfiguration.get("msg.err.internal.insert.exception");
        this.searchException = messagesConfiguration.get("msg.err.internal.search.exception");
    }

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

    public ErrorMessage sessaoInvalida() {
        return this.setMensagem(this.sessaoInvalida)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage opcaoInvalida() {
        return this.setMensagem(this.opcaoInvalida)
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

    public ErrorMessage pautaJaExisteComNome() {
        return this.setMensagem(this.pautaJaExisteComNome)
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public ErrorMessage insertException() {
        return this.setMensagem(this.insertException)
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorMessage searchException() {
        return this.setMensagem(this.searchException)
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ErrorMessage setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setMessagesConfiguration(MessagesConfiguration configuration) {
        this.messagesConfiguration = configuration;
    }

}