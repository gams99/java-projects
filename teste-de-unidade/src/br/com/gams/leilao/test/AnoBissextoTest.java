package br.com.gams.leilao.test;

import br.com.gams.leilao.dominio.AnoBissexto;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnoBissextoTest {

    @Test
    public void VerificaSeEhBissexto(){

        AnoBissexto anoBissexto = new AnoBissexto();
        int year = 2020;
        anoBissexto.verifica(year);

        assertTrue(anoBissexto.verifica(year));

    }


}
