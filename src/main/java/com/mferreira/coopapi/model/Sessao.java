package com.mferreira.coopapi.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "SESSAO", catalog = "softdesign")
public class Sessao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @OneToOne
    @JoinColumn(name = "ID_PAUTA", referencedColumnName = "ID", insertable = false, updatable = false)
    private Pauta pauta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fim;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @NotNull
    private Date inicio;
    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private Date updatedAt;
    @Column(name = "active", columnDefinition = "boolean DEFAULT true")
    private Boolean active;

    public Sessao() {}

    public Sessao(Long id, Pauta pauta, Date inicio, Date fim, java.sql.Date createdAt, java.sql.Date updatedAt, Boolean active) {
        this.id = id;
        this.pauta = pauta;
        this.inicio = inicio;
        this.fim = fim;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
