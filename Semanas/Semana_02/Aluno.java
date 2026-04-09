package entidades;

import java.sql.Date;
import java.util.Objects;

public class Aluno {
    private int id;
    private String nome;
    private String sexo;
    private Date dt_nasc;

    public Aluno(int id, String nome, String sexo, Date dt_nasc) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dt_nasc = dt_nasc;
    }

    public Aluno(String nome, String sexo, Date dt_nasc) {
        this.nome = nome;
        this.sexo = sexo;
        this.dt_nasc = dt_nasc;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public Date getDt_nasc() { return dt_nasc; }
    public void setDt_nasc(Date dt_nasc) { this.dt_nasc = dt_nasc; }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", dt_nasc=" + dt_nasc + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(dt_nasc, nome, sexo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Aluno other = (Aluno) obj;
        return Objects.equals(dt_nasc, other.dt_nasc) && Objects.equals(nome, other.nome)
                && Objects.equals(sexo, other.sexo);
    }
}