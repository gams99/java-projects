package com.gams.forum.repository;

import com.gams.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCursoNome(String nomeCurso); //Jpa searching automatically by class Curso and attribute Nome
}
