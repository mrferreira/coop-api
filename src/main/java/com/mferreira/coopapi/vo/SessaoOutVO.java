package com.mferreira.coopapi.vo;

import com.mferreira.coopapi.model.Pauta;

import java.util.Date;

public class SessaoOutVO {
    private Long id;
    private Pauta pauta;
    private Date inicio;
    private Date fim;
    private Date createdAt;
    private Date updatedAt;
    private Boolean active = true;

    public SessaoOutVO() {}

    public SessaoOutVO(Long id, Pauta pauta, Date inicio, Date fim, Date createdAt, Date modifiedAt, Boolean active) {
        this.id = id;
        this.pauta = pauta;
        this.inicio = inicio;
        this.fim = fim;
        this.createdAt = createdAt;
        this.updatedAt = modifiedAt;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
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
