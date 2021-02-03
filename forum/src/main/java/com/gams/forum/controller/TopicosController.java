package com.gams.forum.controller;

import com.gams.forum.controller.dto.DetalhesDoTopicoDto;
import com.gams.forum.controller.dto.TopicoDto;
import com.gams.forum.controller.form.TopicoForm;
import com.gams.forum.model.Topico;
import com.gams.forum.repository.CursoRepository;
import com.gams.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){

        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso); //this method is created with TopicoRepository helps,
            return TopicoDto.converter(topicos); // example =>   topicos?nomeCurso=Spring+Boot
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) { //dto, metodo para pegar URI do corpo, valid para validar com BEAN VALIDATION
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //criacao de um POST de criacao
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}") // detalhar um topico
    public DetalhesDoTopicoDto detalhar(@PathVariable Long id){ //variavel da url e nao do corpo
        Topico topico = topicoRepository.getOne(id);
        return new DetalhesDoTopicoDto(topico);
    }
}
