package view;

import java.util.List;
import java.util.Scanner;

import controller.EditoraController;
import entity.Editora;

public class TelaEditora {

    private EditoraController controller;
    private Scanner scanner;

    public TelaEditora(EditoraController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Editoras ---");
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
        System.out.print("Nome da editora: ");
        String nome = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        controller.salvar(nome, cidade);
    }

    private void listar() {
        List<Editora> editoras = controller.listarTodos();
        System.out.println("\nLista de Editoras:");
        for (Editora e : editoras) {
            System.out.println(e.getId() + " - " + e.getNome() + " - " + e.getCidade());
        }
    }

    private void atualizar() {
        System.out.print("ID da editora: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Editora e = controller.buscarPorId(id);
        if (e == null) {
            System.out.println("Editora não encontrada.");
            return;
        }
        System.out.print("Novo nome (" + e.getNome() + "): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = e.getNome();
        System.out.print("Nova cidade (" + e.getCidade() + "): ");
        String cidade = scanner.nextLine();
        if (cidade.isEmpty()) cidade = e.getCidade();
        controller.atualizar(id, nome, cidade);
    }

    private void excluir() {
        System.out.print("ID da editora: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.excluir(id);
    }
}
