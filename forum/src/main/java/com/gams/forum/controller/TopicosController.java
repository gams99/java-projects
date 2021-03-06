package com.gams.forum.controller;

import com.gams.forum.controller.dto.DetalhesDoTopicoDto;
import com.gams.forum.controller.dto.TopicoDto;
import com.gams.forum.controller.form.AttTopicoForm;
import com.gams.forum.controller.form.TopicoForm;
import com.gams.forum.model.Topico;
import com.gams.forum.repository.CursoRepository;
import com.gams.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value="listaDeTopicos")
    public Page<TopicoDto> lista(@RequestParam (required = false) String nomeCurso, //RequestParam p receive param in url
                                @PageableDefault(sort = "id", page = 0, size = 10) Pageable paginacao){ // more easier pagination -> /topicos?page=0&size=10&sort=id,asc

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao); //this method is created with TopicoRepository helps,
            return TopicoDto.converter(topicos); // example =>   topicos?nomeCurso=Spring+Boot
        }
    }

    @PostMapping
    @Transactional //para att no db
    @CacheEvict(value="listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) { //dto, metodo para pegar URI do corpo, valid para validar com BEAN VALIDATION
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //criacao de um POST de criacao
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}") // detalhar um topico
    public ResponseEntity <DetalhesDoTopicoDto> detalhar(@PathVariable Long id){ //variavel da url e nao do corpo
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional //para att no db
    @CacheEvict(value="listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AttTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();
    }




    @DeleteMapping("/{id}")
    @Transactional //para att no db
    @CacheEvict(value="listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}
