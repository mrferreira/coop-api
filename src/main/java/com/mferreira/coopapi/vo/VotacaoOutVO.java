package com.mferreira.coopapi.vo;

public class VotacaoOutVO {

    private Long id;
    private String cpf;
    private String opcao;
    private SessaoEntryVO sessao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SessaoEntryVO getSessao() {
        return sessao;
    }

    public void setSessao(SessaoEntryVO sessao) {
        this.sessao = sessao;
    }
}
