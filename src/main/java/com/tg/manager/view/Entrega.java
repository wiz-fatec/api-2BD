package com.tg.manager.view;

public class Entrega {
    private String atividade;
    private String tipoTG;
    private String dataInicial;
    private String dataFinal;
    public String getAtividade() {
        return atividade;
    }
    public Entrega(String atividade, String tipoTG, String dataInicial, String dataFinal) {
        this.atividade = atividade;
        this.tipoTG = tipoTG;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }
    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }
    public String getTipoTG() {
        return tipoTG;
    }
    public void setTipoTG(String tipoTG) {
        this.tipoTG = tipoTG;
    }
    public String getDataInicial() {
        return dataInicial;
    }
    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }
    public String getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
}    