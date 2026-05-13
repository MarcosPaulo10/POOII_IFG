package view;

import java.util.List;
import java.util.Scanner;

import controller.AutorController;
import entity.Autor;

public class TelaAutor {

    private AutorController controller;
    private Scanner scanner;

    public TelaAutor(AutorController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Autores ---");
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
        System.out.print("Nome do autor: ");
        String nome = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();
        controller.salvar(nome, nacionalidade);
    }

    private void listar() {
        List<Autor> autores = controller.listarTodos();
        System.out.println("\nLista de Autores:");
        for (Autor a : autores) {
            System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getNacionalidade());
        }
    }

    private void atualizar() {
        System.out.print("ID do autor: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Autor a = controller.buscarPorId(id);
        if (a == null) {
            System.out.println("Autor não encontrado.");
            return;
        }
        System.out.print("Novo nome (" + a.getNome() + "): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = a.getNome();
        System.out.print("Nova nacionalidade (" + a.getNacionalidade() + "): ");
        String nacionalidade = scanner.nextLine();
        if (nacionalidade.isEmpty()) nacionalidade = a.getNacionalidade();
        controller.atualizar(id, nome, nacionalidade);
    }

    private void excluir() {
        System.out.print("ID do autor: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.excluir(id);
    }
}
