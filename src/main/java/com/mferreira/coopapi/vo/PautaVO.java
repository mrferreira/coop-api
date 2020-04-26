package com.mferreira.coopapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PautaVO {
    private Long id;
    private String nome;
    private Date createdAt;
    private Date updatedAt;
    private Boolean active = true;

    public PautaVO() {}

    public PautaVO(Long id, String nome,
                   Date createdAt, Date updatedAt) {
        this.id = id;
        this.nome = nome;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
