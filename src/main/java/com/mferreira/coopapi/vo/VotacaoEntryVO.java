package com.mferreira.coopapi.vo;

public class VotacaoEntryVO {

    private String cpf;
    private String opcao;
    private SessaoVO sessao;

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

    public SessaoVO getSessao() {
        return sessao;
    }

    public void setSessao(SessaoVO sessao) {
        this.sessao = sessao;
    }
}
