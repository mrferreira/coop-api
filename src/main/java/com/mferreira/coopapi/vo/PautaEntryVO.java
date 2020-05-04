package com.mferreira.coopapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PautaEntryVO {
    private String nome;
    private Date createdAt;
    private Date updatedAt;
    private Boolean active = true;

    public PautaEntryVO() {}

    public PautaEntryVO(String nome,
                        Date createdAt, Date updatedAt) {
        this.nome = nome;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
