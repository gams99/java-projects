package br.com.gams.leilao.test;

import br.com.gams.leilao.dominio.AnoBissexto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnoBissextoTest {

    @Test
    public void deveRetornarAnoBissexto(){

        AnoBissexto anoBissexto = new AnoBissexto();
        int year = 2020;
        int year2 = 2024;
        anoBissexto.verifica(year);

        assertTrue(anoBissexto.verifica(year));
        assertTrue(anoBissexto.verifica(year2));
    }
    @Test
    public void naoDeveRetornarAnoBissexto() {

        AnoBissexto anoBissexto = new AnoBissexto();

        int year = 2019;
        int year2 = 2022;
        int year3 = 2027;

        assertFalse(anoBissexto.verifica(year));
        assertFalse(anoBissexto.verifica(year2));

        //outra forma de testar
        assertEquals(false, anoBissexto.verifica(year3));
        assertEquals(false, anoBissexto.verifica(2083));
    }


}
