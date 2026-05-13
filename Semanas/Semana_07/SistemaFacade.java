package controller;

import java.util.Scanner;
import model.dao.PostgresDAOFactory;
import view.TelaAluno;
import view.TelaCurso;
import view.TelaDisciplina;

public class SistemaFacade {
    private Scanner scanner;
    private TelaAluno telaAluno;
    private TelaCurso telaCurso;
    private TelaDisciplina telaDisciplina;

    public SistemaFacade() {
        scanner = new Scanner(System.in);
        PostgresDAOFactory factory = new PostgresDAOFactory();
        AlunoController alunoController = new AlunoController(factory);
        CursoController cursoController = new CursoController(factory);
        DisciplinaController disciplinaController = new DisciplinaController(factory);

        telaAluno = new TelaAluno(alunoController, scanner);
        telaCurso = new TelaCurso(cursoController, scanner);
        telaDisciplina = new TelaDisciplina(disciplinaController, scanner);
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n===== SISTEMA ESCOLAR =====");
            System.out.println("1 - Gerenciar Alunos");
            System.out.println("2 - Gerenciar Cursos");
            System.out.println("3 - Gerenciar Disciplinas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> telaAluno.exibirMenu();
                case 2 -> telaCurso.exibirMenu();
                case 3 -> telaDisciplina.exibirMenu();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
