package model.entities;

public class Curso {
    private Integer idcurso;
    private String nomecurso;

    public Curso() {
    }

    public Curso(Integer idcurso, String nomecurso) {
        this.idcurso = idcurso;
        this.nomecurso = nomecurso;
    }

    public Integer getIdcurso() { return idcurso; }
    public void setIdcurso(Integer idcurso) { this.idcurso = idcurso; }
    public String getNomecurso() { return nomecurso; }
    public void setNomecurso(String nomecurso) { this.nomecurso = nomecurso; }

    @Override
    public String toString() {
        return "Curso [id=" + idcurso + ", nome=" + nomecurso + "]";
    }
}
