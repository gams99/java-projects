package com.gams.forum.repository;

import com.gams.forum.model.Curso;
import com.gams.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nome); //
}