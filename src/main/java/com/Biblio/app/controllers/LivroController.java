package com.Biblio.app.controllers;

import com.Biblio.app.dto.LivroDTO;
import com.Biblio.app.entities.Livro;
import com.Biblio.app.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("book")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping("add")
    public ResponseEntity<LivroDTO> addLivro(@RequestBody LivroDTO dto){
        dto = livroService.addLivro(dto);
        return  ResponseEntity.ok(dto);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Livro> getLivroId(@PathVariable Long id){
        var livro = livroService.getLivro(id);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Livro> atlzLivroId(@PathVariable Long id, @RequestBody LivroDTO dto){
         livroService.atualizarLivro(dto, id);
        return ResponseEntity.ok().build();
    }

}
