package view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AutorController;
import controller.EditoraController;
import controller.LivroController;
import entity.Autor;
import entity.Editora;
import entity.Livro;
import entity.TipoPublicacao;

public class TelaLivro {

    private LivroController livroController;
    private EditoraController editoraController;
    private AutorController autorController;
    private Scanner scanner;

    public TelaLivro(LivroController livroController, EditoraController editoraController,
                     AutorController autorController, Scanner scanner) {
        this.livroController = livroController;
        this.editoraController = editoraController;
        this.autorController = autorController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Livros ---");
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
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano de publicação: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Preço: ");
        BigDecimal preco = new BigDecimal(scanner.nextLine());

        System.out.println("Tipo de publicação:");
        System.out.println("1 - IMPRESSO  2 - DIGITAL  3 - AUDIOBOOK");
        System.out.print("Opção: ");
        int tipoOpcao = scanner.nextInt();
        scanner.nextLine();
        TipoPublicacao tipo = switch (tipoOpcao) {
            case 2 -> TipoPublicacao.DIGITAL;
            case 3 -> TipoPublicacao.AUDIOBOOK;
            default -> TipoPublicacao.IMPRESSO;
        };

        for (Editora e : editoraController.listarTodos()) {
            System.out.println(e.getId() + " - " + e.getNome());
        }
        System.out.print("ID da editora: ");
        int editoraId = scanner.nextInt();
        scanner.nextLine();

        for (Autor a : autorController.listarTodos()) {
            System.out.println(a.getId() + " - " + a.getNome());
        }
        System.out.print("IDs dos autores (separados por vírgula): ");
        String[] partes = scanner.nextLine().split(",");
        List<Integer> autorIds = new ArrayList<>();
        for (String parte : partes) {
            try {
                autorIds.add(Integer.parseInt(parte.trim()));
            } catch (NumberFormatException ignored) {
            }
        }

        livroController.salvar(titulo, ano, isbn, preco, tipo, editoraId, autorIds);
    }

    private void listar() {
        List<Livro> livros = livroController.listarTodos();
        System.out.println("\nLista de Livros:");
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    private void atualizar() {
        System.out.print("ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Livro l = livroController.buscarPorId(id);
        if (l == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.print("Novo título (" + l.getTitulo() + "): ");
        String titulo = scanner.nextLine();
        if (titulo.isEmpty()) titulo = l.getTitulo();
        System.out.print("Novo ano (" + l.getAnoPublicacao() + "): ");
        String anoStr = scanner.nextLine();
        int ano = anoStr.isEmpty() ? l.getAnoPublicacao() : Integer.parseInt(anoStr);
        System.out.print("Novo ISBN (" + l.getIsbn() + "): ");
        String isbn = scanner.nextLine();
        if (isbn.isEmpty()) isbn = l.getIsbn();
        System.out.print("Novo preço (" + l.getPreco() + "): ");
        String precoStr = scanner.nextLine();
        BigDecimal preco = precoStr.isEmpty() ? l.getPreco() : new BigDecimal(precoStr);
        System.out.println("Tipo atual: " + l.getTipo() + " | 1-IMPRESSO  2-DIGITAL  3-AUDIOBOOK  (Enter = manter)");
        String tipoStr = scanner.nextLine();
        TipoPublicacao tipo = switch (tipoStr) {
            case "2" -> TipoPublicacao.DIGITAL;
            case "3" -> TipoPublicacao.AUDIOBOOK;
            case "1" -> TipoPublicacao.IMPRESSO;
            default -> l.getTipo();
        };
        livroController.atualizar(id, titulo, ano, isbn, preco, tipo);
    }

    private void excluir() {
        System.out.print("ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        livroController.excluir(id);
    }
}
