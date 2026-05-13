package observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.entities.Curso;

public class LogObserver implements CursoObserver {
    
	@Override
	public void onCursoCadastrado(Curso curso) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("[LOG] Curso \"" + curso.getNomecurso() + "\" cadastrado em " + dataHora);
    }
	
}
