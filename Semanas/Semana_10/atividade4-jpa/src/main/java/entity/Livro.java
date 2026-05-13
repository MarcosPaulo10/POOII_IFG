package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "ano_publicacao", nullable = false)
    private int anoPublicacao;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPublicacao tipo;

    @ManyToOne
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    @ManyToMany
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    public Livro() {
    }

    public Livro(Integer id, String titulo, int anoPublicacao, String isbn, BigDecimal preco,
                 TipoPublicacao tipo, Editora editora) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
        this.preco = preco;
        this.tipo = tipo;
        this.editora = editora;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public TipoPublicacao getTipo() { return tipo; }
    public void setTipo(TipoPublicacao tipo) { this.tipo = tipo; }
    public Editora getEditora() { return editora; }
    public void setEditora(Editora editora) { this.editora = editora; }
    public List<Autor> getAutores() { return autores; }

    public void adicionarAutor(Autor a) {
        this.autores.add(a);
        a.getLivros().add(this);
    }

    public void removerAutor(Autor a) {
        this.autores.remove(a);
        a.getLivros().remove(this);
    }

    @Override
    public String toString() {
        return "Livro [id=" + id + ", titulo=" + titulo + ", ano=" + anoPublicacao
                + ", isbn=" + isbn + ", preco=" + preco + ", tipo=" + tipo
                + ", editora=" + (editora != null ? editora.getNome() : "null") + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Livro other = (Livro) obj;
        return Objects.equals(isbn, other.isbn);
    }
}
