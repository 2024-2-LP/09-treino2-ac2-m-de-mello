package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        setNome(nome);
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null)
            throw new ArgumentoInvalidoException("Livro Inválido.");
        if (livro.getTitulo() == null || livro.getTitulo().isBlank())
            throw new ArgumentoInvalidoException("Titulo do Livro Inválido.");
        if (livro.getAutor() == null || livro.getAutor().isBlank())
            throw new ArgumentoInvalidoException("Autor do Livro Inválido.");
        if (livro.getDataPublicacao() == null)
            throw new ArgumentoInvalidoException("Data de Publicação do Livro Inválida.");
        this.livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo) {
        this.livros.remove(buscarLivroPorTitulo(titulo));
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank())
            throw new ArgumentoInvalidoException("Título Inválido.");
        return this.livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não Encontrado."));
    }

    public Integer contarLivros() {
        return this.livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        if (ano == null)
            throw new ArgumentoInvalidoException("Ano inválido.");
        return this.livros.stream()
                .filter(livro -> livro.getDataPublicacao().getYear() <= ano)
                .toList();
    }

    public List<Livro> retornarTopCincoLivros() {
        return this.livros.stream()
                .sorted(Comparator.comparingDouble(Livro::calcularMediaAvaliacoes).reversed())
                .limit(5)
                .toList();
    }
}
