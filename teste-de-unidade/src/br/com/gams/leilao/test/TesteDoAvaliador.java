package br.com.gams.leilao.test;

import br.com.gams.leilao.dominio.Avaliador;
import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


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

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){

        Usuario joao = new Usuario("Joao");
        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 1000.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEntenderOs3MaioresLances(){
        Usuario jose = new Usuario("Jose");
        Usuario joao = new Usuario("Joao");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(jose, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.get3Maiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0,maiores.get(0).getValor(), 0.0001);
        assertEquals(300.0,maiores.get(1).getValor(), 0.0001);
        assertEquals(200.0,maiores.get(2).getValor(), 0.0001);

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
        assertEquals(mediaEsperada, leiloeiro.getMedia(), 0.00001);
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
        assertEquals(0, avaliador.getMedia(), 0.0001);

    }
}
