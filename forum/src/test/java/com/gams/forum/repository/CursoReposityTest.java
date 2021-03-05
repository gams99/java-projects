package com.gams.forum.repository;

import com.gams.forum.model.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CursoReposityTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void shouldFindByNome() {
        String nomeCurso = "HTML 5";
        Curso curso = repository.findByNome(nomeCurso);
        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void shouldNotFindByNome() {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);
        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }
}