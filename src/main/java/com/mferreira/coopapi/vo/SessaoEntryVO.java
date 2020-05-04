package com.mferreira.coopapi.vo;

import java.util.Date;

public class SessaoEntryVO {
    private PautaSessaoEntryVO pauta;
    private Date inicio;
    private Date fim;

    public SessaoEntryVO() {}

    public SessaoEntryVO(PautaSessaoEntryVO pauta, Date inicio, Date fim) {
        this.pauta = pauta;
        this.inicio = inicio;
        this.fim = fim;
    }

    public PautaSessaoEntryVO getPauta() {
        return pauta;
    }

    public void setPauta(PautaSessaoEntryVO pauta) {
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
}
