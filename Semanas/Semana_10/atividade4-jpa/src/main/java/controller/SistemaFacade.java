package controller;

import java.util.Scanner;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import view.TelaAutor;
import view.TelaEditora;
import view.TelaLivro;

public class SistemaFacade {

    private Scanner scanner;
    private TelaEditora telaEditora;
    private TelaAutor telaAutor;
    private TelaLivro telaLivro;
    private EntityManagerFactory emf;

    public SistemaFacade() {
        emf = Persistence.createEntityManagerFactory("atividade4jpa");
        scanner = new Scanner(System.in);

        EditoraController editoraController = new EditoraController(emf);
        AutorController autorController = new AutorController(emf);
        LivroController livroController = new LivroController(emf);

        telaEditora = new TelaEditora(editoraController, scanner);
        telaAutor = new TelaAutor(autorController, scanner);
        telaLivro = new TelaLivro(livroController, editoraController, autorController, scanner);
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Gerenciar Editoras");
            System.out.println("2 - Gerenciar Autores");
            System.out.println("3 - Gerenciar Livros");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> telaEditora.exibirMenu();
                case 2 -> telaAutor.exibirMenu();
                case 3 -> telaLivro.exibirMenu();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        emf.close();
    }

    public static void main(String[] args) {
        new SistemaFacade().executar();
    }
}
