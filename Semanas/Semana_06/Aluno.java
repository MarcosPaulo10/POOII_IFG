package model.entities;

import java.util.Date;

public class Aluno {
    private Integer id;
    private String nome;
    private String sexo;
    private Date dt_nasc;
    private Curso curso; // associação opcional

    public Aluno() {
    }

    public Aluno(Integer id, String nome, String sexo, Date dt_nasc) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dt_nasc = dt_nasc;
    }

    public Aluno(Integer id, String nome, String sexo, Date dt_nasc, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dt_nasc = dt_nasc;
        this.curso = curso;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public Date getDt_nasc() { return dt_nasc; }
    public void setDt_nasc(Date dt_nasc) { this.dt_nasc = dt_nasc; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc + "]";
    }
}
