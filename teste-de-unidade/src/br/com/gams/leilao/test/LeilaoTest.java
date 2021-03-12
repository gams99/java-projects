package br.com.gams.leilao.test;

import br.com.gams.leilao.builder.CriadorDeLeilao;
import br.com.gams.leilao.dominio.Avaliador;
import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {


    @Test
    public void deveReceberUmLance(){

        Leilao leilao = new CriadorDeLeilao().para("Mackbook Pro 15")
                .lance(new Usuario("Steve Wozniak"), 2000.0)
                .constroi();

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLance(){

        Leilao leilao = new CriadorDeLeilao().para("Mackbook Pro 15")
                .lance(new Usuario("Steve Jobs"), 2000.0)
                .lance(new Usuario("Steve Wozniak"), 3000.0)
                .constroi();

        assertEquals(2, leilao.getLances().size());

        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }
    @Test
    public void deveReceber2LancesSeguidosDoMesmoUsuario(){
        Usuario steveJobs = new Usuario("Steve Jobs");
        Leilao leilao = new CriadorDeLeilao()
                .para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .lance(steveJobs, 3000.0)
                .constroi();

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000)
                .lance(billGates, 3000)
                .lance(steveJobs, 4000)
                .lance(billGates, 5000)
                .lance(steveJobs, 6000)
                .lance(billGates, 7000)
                .lance(steveJobs, 8000)
                .lance(billGates, 9000)
                .lance(steveJobs, 10000)
                .lance(billGates, 11000)
                .lance(steveJobs, 12000)
                .constroi();

        assertEquals(10, leilao.getLances().size());
        int ultimo = leilao.getLances().size()-1;
        assertEquals(11000.0, leilao.getLances().get(ultimo).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }

}