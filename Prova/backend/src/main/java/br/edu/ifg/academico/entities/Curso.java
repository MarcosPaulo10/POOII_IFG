package br.edu.ifg.academico.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcurso;
	
	@Column(name= "nomecurso", nullable = false)
	private String nomecurso;
	
	@ManyToMany
	@JoinTable(
		name = "aluno_curso",
		joinColumns = @JoinColumn(name="idcurso"),
		inverseJoinColumns = @JoinColumn(name="idaluno")
	)
	private List<Aluno> alunos = new ArrayList<>();
	
	public Curso() {

	}

	public Curso(Integer idcurso, String nomecurso) {
		this.idcurso = idcurso;
		this.nomecurso = nomecurso;
	}

	public Integer getIdCurso() {
		return idcurso;
	}

	public void setIdCurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomeCurso() {
		return nomecurso;
	}

	public void setNomeCurso(String nomecurso) {
		this.nomecurso = nomecurso;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	@Override
	public String toString() {
		return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomecurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(nomecurso, other.nomecurso);
	}
	
	public void adicionarAluno(Aluno a) {
		this.alunos.add(a);
		a.getCursos().add(this);
	}
	
	public void removerAluno(Aluno a) {
		this.alunos.remove(a);
		a.getCursos().remove(this);
	}
}
