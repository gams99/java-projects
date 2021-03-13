package br.com.gams.leilao.test;

import br.com.gams.leilao.builder.CriadorDeLeilao;
import br.com.gams.leilao.dominio.Avaliador;
import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;
import org.junit.Assert;
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

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLance(){
            Leilao leilao = new CriadorDeLeilao().para("Headset HyperX").constroi();

            leiloeiro.avalia(leilao);
            Assert.fail();
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){

        Leilao leilao = new CriadorDeLeilao().para("PS5")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .constroi();

        leiloeiro.avalia(leilao);

        double maiorEsperado = 300.0;
        double menorEsperado = 100.0;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){

        Leilao leilao = new CriadorDeLeilao().para("PS5")
                .lance(joao, 100.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(100, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(100, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEntenderOs3MaioresLances(){

        Leilao leilao = new CriadorDeLeilao().para("PS5")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.get3Maiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0,maiores.get(0).getValor(), 0.0001);
        assertEquals(300.0,maiores.get(1).getValor(), 0.0001);
        assertEquals(200.0,maiores.get(2).getValor(), 0.0001);

    }

    @Test
    public void deveCalcularMedia(){

        Leilao leilao = new CriadorDeLeilao().para("PS5")
                .lance(joao, 700.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .constroi();


        leiloeiro.avalia(leilao);

        double mediaEsperada = 400.0;

        System.out.println(leiloeiro.getMedia());
        assertEquals(mediaEsperada, leiloeiro.getMedia(), 0.00001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {

        Leilao leilao = new CriadorDeLeilao().para("PS5")
                .lance(joao, 100.0)
                .lance(maria, 50.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .lance(joao, 100.0)
                .lance(maria, 59.0)
                .constroi();

        criaAvaliador();
        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(50.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {

        Leilao leilao = new CriadorDeLeilao().para("PS5")
                .lance(joao, 700.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .constroi();
        criaAvaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(200.0, leiloeiro.getMenorLance(), 0.0001);
    }
}
