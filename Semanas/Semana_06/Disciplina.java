package model.entities;

public class Disciplina {
    private Integer iddisciplina;
    private String nomedisciplina;
    private Integer cargahoraria;

    public Disciplina() {
    }

    public Disciplina(Integer iddisciplina, String nomedisciplina, Integer cargahoraria) {
        this.iddisciplina = iddisciplina;
        this.nomedisciplina = nomedisciplina;
        this.cargahoraria = cargahoraria;
    }

    public Integer getIddisciplina() { return iddisciplina; }
    public void setIddisciplina(Integer iddisciplina) { this.iddisciplina = iddisciplina; }
    public String getNomedisciplina() { return nomedisciplina; }
    public void setNomedisciplina(String nomedisciplina) { this.nomedisciplina = nomedisciplina; }
    public Integer getCargahoraria() { return cargahoraria; }
    public void setCargahoraria(Integer cargahoraria) { this.cargahoraria = cargahoraria; }

    @Override
    public String toString() {
        return "Disciplina [id=" + iddisciplina + ", nome=" + nomedisciplina + ", carga=" + cargahoraria + "]";
    }
}
