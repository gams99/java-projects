package br.com.gams.leilao.test;

import br.com.gams.leilao.dominio.Avaliador;
import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;


public class TesteDoAvaliador {

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){
        Usuario jose = new Usuario("Jose");
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 700.0));
        leilao.propoe(new Lance(maria, 230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 700.0;
        double menorEsperado = 230.0;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);

        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }
}