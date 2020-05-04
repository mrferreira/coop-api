package com.mferreira.coopapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PautaEntryVO {
    private String nome;

    public PautaEntryVO() {}

    public PautaEntryVO(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
