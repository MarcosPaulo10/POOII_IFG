package telas;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import entidades.Aluno;
import jdbc.AlunoJDBC;

public class AlunoTela {

    public static void limpaTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static void telaMenu() {
        limpaTela();
        System.out.println("\u001B[1m######        Menu        ######\u001B[0m");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Alterar");
        System.out.println("4 - Excluir");
        System.out.println("5 - Sair");
        System.out.print("\n\tOpção: ");
    }

    public static void telaCadastra(Scanner console) throws SQLException {
        limpaTela();
        System.out.println("\u001B[1m### Cadastrar Aluno ###\u001B[0m");

        System.out.print("Nome: ");
        String nome = console.nextLine();

        System.out.print("Sexo: ");
        String sexo = console.nextLine();

        System.out.print("Data de Nascimento (aaaa-mm-dd): ");
        Date dt_nasc = Date.valueOf(console.nextLine());

        if (validarAluno(new Aluno(nome, sexo, dt_nasc))) {
            boolean cadastrado = AlunoJDBC.salvar(new Aluno(nome, sexo, dt_nasc));
            if (cadastrado) {
                System.out.println("\u001B[1mCadastro realizado com sucesso!\u001B[0m");
            } else {
                System.out.println("Não foi possível cadastrar " + nome);
            }
        }
        console.nextLine(); // pausa
    }

    public static void telaListagem(Scanner console, List<Aluno> lista) {
        limpaTela();
        System.out.println("\u001B[1m###### Listagem de Alunos ######\u001B[0m");
        System.out.printf("%-5s  %-20s  %-10s  %-12s%n", "ID", "NOME", "SEXO", "DT_NASC");
        for (Aluno a : lista) {
            System.out.printf("%-5d  %-20s  %-10s  %-12s%n",
                    a.getId(), a.getNome(), a.getSexo(), a.getDt_nasc());
        }
        System.out.println("\u001B[1m#################################\u001B[0m");
        console.nextLine();
    }

    public static void telaAlterar(Scanner console) throws SQLException {
        limpaTela();
        System.out.println("\u001B[1m### Alterar Aluno ###\u001B[0m");

        List<Aluno> lista = AlunoJDBC.listar();
        System.out.printf("%-5s  %-20s  %-10s  %-12s%n", "ID", "NOME", "SEXO", "DT_NASC");
        for (Aluno a : lista) {
            System.out.printf("%-5d  %-20s  %-10s  %-12s%n",
                    a.getId(), a.getNome(), a.getSexo(), a.getDt_nasc());
        }

        System.out.print("\nDigite o ID do aluno a alterar: ");
        int id = console.nextInt();
        console.nextLine();

        Aluno original = lista.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        if (original == null) {
            System.out.println("ID não encontrado.");
            console.nextLine();
            return;
        }

        System.out.print("Digite o novo nome: ");
        String nome = console.nextLine();
        if (nome.isBlank()) nome = original.getNome();

        System.out.print("Digite o novo sexo: ");
        String sexo = console.nextLine();
        if (sexo.isBlank()) sexo = original.getSexo();

        System.out.print("Digite a nova data (aaaa-mm-dd): ");
        String dataStr = console.nextLine();
        Date dt_nasc = dataStr.isBlank() ? original.getDt_nasc() : Date.valueOf(dataStr);

        Aluno alterado = new Aluno(id, nome, sexo, dt_nasc);
        if (validarAluno(alterado) && AlunoJDBC.alterar(alterado)) {
            System.out.println("Alteração realizada com sucesso!");
        } else {
            System.out.println("Erro ao alterar. Verifique os dados.");
        }
        console.nextLine();
    }

    public static void telaExcluir(Scanner console) throws SQLException {
        limpaTela();
        System.out.println("\u001B[1m### Excluir Aluno ###\u001B[0m");

        List<Aluno> lista = AlunoJDBC.listar();
        System.out.printf("%-5s  %-20s  %-10s  %-12s%n", "ID", "NOME", "SEXO", "DT_NASC");
        for (Aluno a : lista) {
            System.out.printf("%-5d  %-20s  %-10s  %-12s%n",
                    a.getId(), a.getNome(), a.getSexo(), a.getDt_nasc());
        }

        System.out.print("Digite o id: ");
        int id = console.nextInt();
        console.nextLine();

        if (AlunoJDBC.apagar(id)) {
            System.out.println("Exclusão realizada com sucesso!");
        } else {
            System.out.println("Erro ao excluir.");
        }
        console.nextLine();
    }

    private static boolean validarAluno(Aluno a) {
        if (a.getNome() == null || a.getNome().trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return false;
        }
        if (a.getSexo() == null || (!a.getSexo().equalsIgnoreCase("Masculino") && !a.getSexo().equalsIgnoreCase("Feminino"))) {
            System.out.println("Sexo deve ser 'Masculino' ou 'Feminino'.");
            return false;
        }
      
        return true;
    }
}