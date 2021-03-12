package br.com.gams.leilao.test;

import br.com.gams.leilao.dominio.Avaliador;
import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class TesteDoAvaliador {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        this.jose = new Usuario("Jose");
        this.joao = new Usuario("Joao");
        this.maria = new Usuario("Maria");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(maria, 300.0));
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 500.0));

        leiloeiro.avalia(leilao);

        double maiorEsperado = 500.0;
        double menorEsperado = 300.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 1000.0));

        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEntenderOs3MaioresLances(){

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(jose, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.get3Maiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0,maiores.get(0).getValor(), 0.0001);
        assertEquals(300.0,maiores.get(1).getValor(), 0.0001);
        assertEquals(200.0,maiores.get(2).getValor(), 0.0001);

    }

    @Test
    public void deveCalcularMedia(){

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(maria, 300.0));
        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 500.0));

        leiloeiro.avalia(leilao);

        double mediaEsperada = 400.0;

        System.out.println(leiloeiro.getMedia());
        assertEquals(mediaEsperada, leiloeiro.getMedia(), 0.00001);
    }

    @Test
    public void testaMediaDeZeroLance(){

        // acao
        Leilao leilao = new Leilao("Iphone 7");


        leiloeiro.avalia(leilao);

        //validacao
        assertEquals(0, leiloeiro.getMedia(), 0.0001);

    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

        criaAvaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
        
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        criaAvaliador();
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }
}
