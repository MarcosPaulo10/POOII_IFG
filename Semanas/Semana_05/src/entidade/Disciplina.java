package entidade;

import java.util.Objects;

public class Disciplina {
    private Integer idDisciplina;
    private String nomedisciplina;
    private Integer cargahoraria;

    public Disciplina(Integer idDisciplina, String nomedisciplina, Integer cargahoraria) {
        this.idDisciplina = idDisciplina;
        this.nomedisciplina = nomedisciplina;
        this.cargahoraria = cargahoraria;
    }

    public Disciplina(String nomedisciplina, Integer cargahoraria) {
        this.nomedisciplina = nomedisciplina;
        this.cargahoraria = cargahoraria;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomedisciplina() {
        return nomedisciplina;
    }

    public void setNomedisciplina(String nomedisciplina) {
        this.nomedisciplina = nomedisciplina;
    }

    public Integer getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(Integer cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargahoraria, idDisciplina, nomedisciplina);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Disciplina other = (Disciplina) obj;
        return Objects.equals(cargahoraria, other.cargahoraria)
                && Objects.equals(idDisciplina, other.idDisciplina)
                && Objects.equals(nomedisciplina, other.nomedisciplina);
    }

    @Override
    public String toString() {
        return "Disciplina [idDisciplina=" + idDisciplina + ", nomedisciplina=" + nomedisciplina + ", cargahoraria="
                + cargahoraria + "]";
    }
}
