package com.Biblio.app.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "tb_livro")
public class Livro {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeLivro;
    private String descricaoLivro;
    private String autor;
    private LocalDate emprestimo;

    public Livro() {
    }

    public Livro(Long id, String nomeLivro, String descricaoLivro, String autor, LocalDate emprestimo) {
        this.id = id;
        this.nomeLivro = nomeLivro;
        this.descricaoLivro = descricaoLivro;
        this.autor = autor;
        this.emprestimo = emprestimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getDescricaoLivro() {
        return descricaoLivro;
    }

    public void setDescricaoLivro(String descricaoLivro) {
        this.descricaoLivro = descricaoLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(LocalDate emprestimo) {
        this.emprestimo = emprestimo;
    }
}
