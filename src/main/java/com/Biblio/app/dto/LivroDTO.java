package com.Biblio.app.dto;

import com.Biblio.app.entities.Livro;

import java.time.LocalDate;

public class LivroDTO {

    private Long id;
    private String nomeLivro;
    private String descricaoLivro;
    private String autor;
    private LocalDate emprestimo;

    public LivroDTO() {
    }

    public LivroDTO(Long id, String nomeLivro, String descricaoLivro, String autor, LocalDate emprestimo) {
        this.id = id;
        this.nomeLivro = nomeLivro;
        this.descricaoLivro = descricaoLivro;
        this.autor = autor;
        this.emprestimo = emprestimo;
    }

    public  LivroDTO(Livro entity){
        id = entity.getId();
        nomeLivro = entity.getNomeLivro();
        autor = entity.getAutor();
        descricaoLivro = entity.getDescricaoLivro();
        emprestimo = entity.getEmprestimo();
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
