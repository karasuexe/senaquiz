package br.com.senac.pi5.services;

public class GrupoPart {

    private int codGrupo;	
    private String nmGrupo;	
    private int codAssunto;	
    private int codLider;	
    private boolean finalizado;
    private int codParticipante;
    private String nmParticipante;	
    private int codCurso;
    private String email;

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public int getCodAssunto() {
        return codAssunto;
    }

    public void setCodAssunto(int codAssunto) {
        this.codAssunto = codAssunto;
    }

    public int getCodLider() {
        return codLider;
    }

    public void setCodLider(int codLider) {
        this.codLider = codLider;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getCodParticipante() {
        return codParticipante;
    }

    public void setCodParticipante(int codParticipante) {
        this.codParticipante = codParticipante;
    }

    public String getNmParticipante() {
        return nmParticipante;
    }

    public void setNmParticipante(String nmParticipante) {
        this.nmParticipante = nmParticipante;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
}
