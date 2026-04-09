package programa;

import java.sql.SQLException;
import java.util.Scanner;
import jdbc.AlunoJDBC;
import jdbc.DB;
import telas.AlunoTela;

public class Principal {

    public static void main(String[] args) {
        try {
            int opcao;
            Scanner console = new Scanner(System.in);
            do {
                AlunoTela.telaMenu();
                opcao = Integer.parseInt(console.nextLine());
                switch (opcao) {
                    case 1:
                        AlunoTela.telaCadastra(console);
                        break;
                    case 2:
                        AlunoTela.telaListagem(console, AlunoJDBC.listar());
                        break;
                    case 3:
                        AlunoTela.telaAlterar(console);
                        break;
                    case 4:
                        AlunoTela.telaExcluir(console);
                        break;
                    case 5:
                        System.out.println("\u001B[1m###### Sistema Finalizado ######\u001B[0m");
                        break;
                    default:
                        System.out.println("Digite um número válido!");
                }
            } while (opcao != 5);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        } finally {
            DB.fechaConexao();
        }
    }
}