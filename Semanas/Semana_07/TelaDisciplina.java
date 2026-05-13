package view;

import java.util.List;
import java.util.Scanner;
import controller.DisciplinaController;
import model.entities.Disciplina;

public class TelaDisciplina {
    private DisciplinaController controller;
    private Scanner scanner;

    public TelaDisciplina(DisciplinaController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Disciplinas ---");
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
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Carga horária: ");
        int carga = scanner.nextInt();
        scanner.nextLine();
        controller.salvar(nome, carga);
    }

    private void listar() {
        List<Disciplina> disciplinas = controller.listarTodos();
        System.out.println("\nLista de Disciplinas:");
        for (Disciplina d : disciplinas) {
            System.out.println(d.getIddisciplina() + " - " + d.getNomedisciplina() + " - " + d.getCargahoraria() + "h");
        }
    }

    private void atualizar() {
        System.out.print("ID da disciplina: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Disciplina d = controller.buscarPorId(id);
        if (d == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }
        System.out.print("Novo nome (" + d.getNomedisciplina() + "): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = d.getNomedisciplina();
        System.out.print("Nova carga horária (" + d.getCargahoraria() + "): ");
        String cargaStr = scanner.nextLine();
        Integer carga = d.getCargahoraria();
        if (!cargaStr.isEmpty()) {
            carga = Integer.parseInt(cargaStr);
        }
        controller.atualizar(id, nome, carga);
    }

    private void excluir() {
        System.out.print("ID da disciplina: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.excluir(id);
    }
}
