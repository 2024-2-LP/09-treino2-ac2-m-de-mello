package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, LocalDate dataPublicacao) {
        setTitulo(titulo);
        setAutor(autor);
        setDataPublicacao(dataPublicacao);
        this.avaliacoes = new ArrayList<>();
    }

    public Livro() {
        this.avaliacoes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrelas) {
        if (descricao == null || descricao.isBlank())
            throw new ArgumentoInvalidoException("Descrição Inválida.");
        if (qtdEstrelas == null || (qtdEstrelas < 0.0 || qtdEstrelas > 5.0))
            throw new ArgumentoInvalidoException("Quantidade de Estrelas Inválidas.");
        getAvaliacoes().add(new Avaliacao(descricao, qtdEstrelas));
    }

    public Double calcularMediaAvaliacoes() {
        return getAvaliacoes().stream()
                .mapToDouble(Avaliacao::getQtdEstrelas)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + getTitulo() + '\'' +
                ", autor='" + getAutor() + '\'' +
                ", dataPublicacao=" + getDataPublicacao() +
                ", mediaAvaliaoes=" + calcularMediaAvaliacoes() +
                '}';
    }
}
