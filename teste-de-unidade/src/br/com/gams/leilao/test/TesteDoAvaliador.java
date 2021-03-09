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

        leilao.propoe(new Lance(maria, 300.0));
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 500.0));


        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 500.0;
        double menorEsperado = 300.0;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveCalcularMedia(){

        Usuario jose = new Usuario("Jose");
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(maria, 300.0));
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 500.0));


        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double mediaEsperada = 400.0;

        System.out.println(leiloeiro.getMedia());
        Assert.assertEquals(mediaEsperada, leiloeiro.getMedia(), 0.00001);
    }

    @Test
    public void testaMediaDeZeroLance(){

        // cenario
        Usuario ewertom = new Usuario("Ewertom");

        // acao
        Leilao leilao = new Leilao("Iphone 7");

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(leilao);

        //validacao
        Assert.assertEquals(0, avaliador.getMedia(), 0.0001);

    }
}
