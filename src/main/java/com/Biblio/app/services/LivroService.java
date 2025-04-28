package com.Biblio.app.services;

import com.Biblio.app.dto.LivroDTO;
import com.Biblio.app.entities.Livro;
import com.Biblio.app.repositories.LivroRepository;
import jakarta.persistence.Cacheable;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.management.MXBean;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    List<Livro> simularCache= new ArrayList<>();


    public  Livro getLivro(Long id) {
        for (int i = 0; i < simularCache.size(); i++){
            Livro livro = simularCache.get(i);
            if(livro.getId() == id){
                System.out.println("\n FOI DO CACHE:");
                System.out.println(livro.getNomeLivro()
                        + "\n" + livro.getDescricaoLivro()
                        + "\n" + livro.getAutor()
                        + "\n" + livro.getEmprestimo()); // Aqui é o divisor de águas!
                return livro;
            }
        }
        Livro livro = livroRepository.findById(id).orElse(null);
        if(livro != null){
            simularCache.add(livro);
            System.out.print("Foi no BANCO");
        } else {
            return null;
        }
        return  livro;
    }

    public LivroDTO addLivro(LivroDTO dto){
        Livro novoLivro = new Livro();
        novoLivro.setDescricaoLivro(dto.getDescricaoLivro());
        novoLivro.setNomeLivro(dto.getNomeLivro());
        novoLivro.setAutor(dto.getAutor());
        novoLivro.setEmprestimo(LocalDate.now());
        novoLivro = livroRepository.save(novoLivro);
        return  new LivroDTO(novoLivro);
    }


    public  Livro atualizarLivro(LivroDTO dto, Long id){
        for(int i = 0; i < simularCache.size(); i++){
            if(simularCache.get(i).getId() == id){
                Optional<Livro> find = livroRepository.findById(id);
                if(find.isPresent()) {
                    Livro livroAntesAtlz = simularCache.get(i);
                    System.out.println("Versão antiga:" + "\n" +livroAntesAtlz);
                    Livro atlzLivro = find.get();
                    atlzLivro.setNomeLivro(dto.getNomeLivro());
                    atlzLivro.setEmprestimo(dto.getEmprestimo());
                    atlzLivro.setDescricaoLivro(dto.getDescricaoLivro());
                    atlzLivro.setAutor(dto.getAutor());
                    atlzLivro = livroRepository.save(atlzLivro);
                    simularCache.set(i, atlzLivro);
                    Livro livroAtualizado = simularCache.get(i);
                    System.out.println("Versão Nova:" + "\n" + livroAtualizado.toString());
                    return  atlzLivro;
                }
            }
        }
        Optional<Livro> find = livroRepository.findById(id);
        if(find.isPresent()) {
            Livro atlzLivro = find.get();
            atlzLivro.setNomeLivro(dto.getNomeLivro());
            atlzLivro.setEmprestimo(dto.getEmprestimo());
            atlzLivro.setDescricaoLivro(dto.getDescricaoLivro());
            atlzLivro.setAutor(dto.getAutor());
            atlzLivro = livroRepository.save(atlzLivro);
            return atlzLivro;
        }
        return null;
    }
}
