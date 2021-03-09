package br.com.gams.leilao.dominio;

import java.text.DecimalFormat;
import java.util.Locale;

public class Avaliador {

    private Double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private Double menorDeTodos = Double.POSITIVE_INFINITY;
    private double media;
    private double total;

    public void avalia(Leilao leilao){
        for(Lance lance : leilao.getLances()){
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total+= lance.getValor();
        }
        if(total == 0) {
            media = 0;
            return;
        }
        media = total/leilao.getLances().size();
    }

    public Double getMedia() {
        return media;
    }

    public Double getMaiorLance() {
        return maiorDeTodos;
    }

    public Double getMenorLance() {
        return menorDeTodos;
    }
}
