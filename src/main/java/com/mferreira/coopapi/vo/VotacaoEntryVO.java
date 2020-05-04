package com.mferreira.coopapi.vo;

public class VotacaoEntryVO {

    private String cpf;
    private String opcao;
    private SessaoVotacaoEntryVO sessao;

    public VotacaoEntryVO() {}

    public VotacaoEntryVO(String cpf, String opcao, SessaoVotacaoEntryVO sessao) {
        this.cpf = cpf;
        this.opcao = opcao;
        this.sessao = sessao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public SessaoVotacaoEntryVO getSessao() {
        return sessao;
    }

    public void setSessao(SessaoVotacaoEntryVO sessao) {
        this.sessao = sessao;
    }
}
