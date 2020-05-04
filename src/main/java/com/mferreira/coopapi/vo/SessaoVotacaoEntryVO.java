package com.mferreira.coopapi.vo;

public class SessaoVotacaoEntryVO {
    private Long id;

    public SessaoVotacaoEntryVO() {}

    public SessaoVotacaoEntryVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
