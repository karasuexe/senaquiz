package br.com.senac.pi5.services;

public class Professor {

    private int    codProfessor;
    private String nome;
    private String email;
    private String senha;
    private String idSenac;
    private String tipo;

    public int getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(int codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIdSenac() {
        return idSenac;
    }

    public void setIdSenac(String idSenac) {
        this.idSenac = idSenac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}