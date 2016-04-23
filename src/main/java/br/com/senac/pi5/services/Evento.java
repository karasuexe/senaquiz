package br.com.senac.pi5.services;

import java.sql.Date;

public class Evento {

    private int codEvento;
    private String descricao;
    private Date data;
    private int codTipoEvento;
    private String codStatus;
    private int codProfessor;
    private String identificador;
    
    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodTipoEvento() {
        return codTipoEvento;
    }

    public void setCodTipoEvento(int codTipoEvento) {
        this.codTipoEvento = codTipoEvento;
    }

    public String getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(String codStatus) {
        this.codStatus = codStatus;
    }

    public int getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(int codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }    
}
