package br.com.gams.leilao.builder;

import br.com.gams.leilao.dominio.Lance;
import br.com.gams.leilao.dominio.Leilao;
import br.com.gams.leilao.dominio.Usuario;

public class CriadorDeLeilao {

    private Leilao leilao;

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() {
        return leilao;
    }
}
