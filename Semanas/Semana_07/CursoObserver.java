package observer;

import model.entities.Curso;

public interface CursoObserver {
    void onCursoCadastrado(Curso curso);
}
