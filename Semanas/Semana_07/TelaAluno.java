package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import controller.AlunoController;
import model.entities.Aluno;

public class TelaAluno {
    private AlunoController controller;
    private Scanner scanner;

    public TelaAluno(AlunoController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Alunos ---");
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
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        try {
            Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            controller.salvar(nome, sexo, dt);
        } catch (Exception e) {
            System.out.println("Data inválida!");
        }
    }

    private void listar() {
        List<Aluno> alunos = controller.listarTodos();
        System.out.println("\nLista de Alunos:");
        for (Aluno a : alunos) {
            System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getSexo() + " - " + a.getDt_nasc());
        }
    }

    private void atualizar() {
        System.out.print("ID do aluno: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Aluno a = controller.buscarPorId(id);
        if (a == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }
        System.out.print("Novo nome (" + a.getNome() + "): ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()) nome = a.getNome();
        System.out.print("Novo sexo (" + a.getSexo() + "): ");
        String sexo = scanner.nextLine();
        if (sexo.isEmpty()) sexo = a.getSexo();
        System.out.print("Nova data (dd/MM/yyyy) (" + new SimpleDateFormat("dd/MM/yyyy").format(a.getDt_nasc()) + "): ");
        String dataStr = scanner.nextLine();
        Date dt = a.getDt_nasc();
        if (!dataStr.isEmpty()) {
            try {
                dt = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            } catch (Exception e) {
                System.out.println("Data inválida, mantendo anterior.");
            }
        }
        controller.atualizar(id, nome, sexo, dt);
    }

    private void excluir() {
        System.out.print("ID do aluno: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.excluir(id);
    }
}
