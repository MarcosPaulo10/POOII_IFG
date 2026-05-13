package view;

import java.util.List;
import java.util.Scanner;
import controller.CursoController;
import model.entities.Curso;

public class TelaCurso {
    private CursoController controller;
    private Scanner scanner;

    public TelaCurso(CursoController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Cursos ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> excluir();
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        controller.salvar(nome);
    }

    private void listar() {
        List<Curso> cursos = controller.listarTodos();
        System.out.println("\nLista de Cursos:");
        for (Curso c : cursos) {
            System.out.println(c.getIdcurso() + " - " + c.getNomecurso());
        }
    }

    private void atualizar() {
        System.out.print("ID do curso: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Curso c = controller.buscarPorId(id);
        if (c == null) {
            System.out.println("Curso não encontrado.");
            return;
        }
        System.out.print("Novo nome (" + c.getNomecurso() + "): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = c.getNomecurso();
        controller.atualizar(id, nome);
    }

    private void excluir() {
        System.out.print("ID do curso: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.excluir(id);
    }
}
