package br.com.gams.leilao.test;

import br.com.gams.leilao.dominio.Avaliador;
import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;

public class TesteDoAvaliador {
    public static void main(String[] args) {
        Usuario jose = new Usuario("Jose");
        Usuario joao = new Usuario("Joao");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 400.0));
        leilao.propoe(new Lance(jose, 700.0));
        leilao.propoe(new Lance(maria, 230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        System.out.println("Maior lance:"+leiloeiro.getMaiorLance());
        System.out.println("Menor lance:"+ leiloeiro.getMenorLance());
    }
}