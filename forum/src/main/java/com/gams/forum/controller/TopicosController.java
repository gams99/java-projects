package com.gams.forum.controller;

import com.gams.forum.controller.dto.TopicoDto;
import com.gams.model.Curso;
import com.gams.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(){

        Topico topico = new Topico("Dúvida", "Dúvida com Spring",
                new Curso("Spring", "Programação"));

        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}
