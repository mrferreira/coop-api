package com.mferreira.coopapi.vo;

public class ResultadoVotacaoVO {
    private boolean votacaoEncerrada;
    private Long votouSim;
    private Long votouNao;

    public ResultadoVotacaoVO(boolean votacaoEncerrada, Long votouSim, Long votouNao) {
        this.votacaoEncerrada = votacaoEncerrada;
        this.votouSim = votouSim;
        this.votouNao = votouNao;
    }

    public boolean isVotacaoEncerrada() {
        return votacaoEncerrada;
    }

    public void setVotacaoEncerrada(boolean votacaoEncerrada) {
        this.votacaoEncerrada = votacaoEncerrada;
    }

    public Long getVotouSim() {
        return votouSim;
    }

    public void setVotouSim(Long votouSim) {
        this.votouSim = votouSim;
    }

    public Long getVotouNao() {
        return votouNao;
    }

    public void setVotouNao(Long votouNao) {
        this.votouNao = votouNao;
    }
}
