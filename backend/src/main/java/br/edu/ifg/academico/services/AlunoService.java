package br.edu.ifg.academico.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifg.academico.entities.Aluno;
import br.edu.ifg.academico.repositories.AlunoRepository;
import br.edu.ifg.academico.services.exceptions.ResourceNotFoundException;

@Service
public class AlunoService {

    private final AlunoRepository alunoRep;

    public AlunoService(AlunoRepository alunoRep) {
        this.alunoRep = alunoRep;
    }

    public List<Aluno> findAll() {
        return alunoRep.findAll();
    }

    public Aluno findById(Integer id) {
        return alunoRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + id));
    }

    public Aluno insert(Aluno a) {
        return alunoRep.save(a);
    }

    public Aluno update(Integer id, Aluno alunoAlt) {
        return alunoRep.findById(id).map(alunoDB -> {
            alunoDB.setNome(alunoAlt.getNome());
            alunoDB.setSexo(alunoAlt.getSexo());
            alunoDB.setDt_nasc(alunoAlt.getDt_nasc());
            return alunoRep.save(alunoDB);
        }).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + id));
    }

    public void delete(Integer id) {
        if (!alunoRep.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado: " + id);
        }
        alunoRep.deleteById(id);
    }
}
