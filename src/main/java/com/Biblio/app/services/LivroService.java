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
        for (Livro livro : simularCache){
            if(id == simularCache.getFirst().getId()){
                System.out.println("Salvou no cache");
                return  simularCache.getFirst();
            }
        }
        Livro livro = livroRepository.findById(id).orElse(null);
        if(livro != null){
            simularCache.add(livro);
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

        for( Livro livro = novoLivro : simularCache){

        }   
        return  new LivroDTO(novoLivro);
    }
}
