package com.mferreira.coopapi.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VOTO", catalog = "softdesign",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_sessao", "cpf"}))
public class Voto {
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @OneToOne
    @JoinColumn(name = "ID_SESSAO",
            referencedColumnName = "ID",
            nullable = false)
    private Sessao sessao;
    @Column(name = "OPCAO", nullable = false)
    private String opcao;
    @Column(name = "CPF", nullable = false)
    private String cpf;
    @Column(name = "CREATED_AT")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
}
