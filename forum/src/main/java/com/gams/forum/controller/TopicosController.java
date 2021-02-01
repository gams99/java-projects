package com.gams.forum.controller;

import com.gams.forum.controller.dto.TopicoDto;
import com.gams.forum.model.Topico;
import com.gams.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso){

        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso); //this method is created with TopicoRepository helps,
            return TopicoDto.converter(topicos); // example =>   topicos?nomeCurso=Spring+Boot
        }
    }
}
