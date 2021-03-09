package br.com.gams.leilao.dominio;

public class Avaliador {

    private Double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private Double menorDeTodos = Double.POSITIVE_INFINITY;

    public void avalia(Leilao leilao){
        for(Lance lance : leilao.getLances()){
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
        }
    }

    public Double getMaiorLance() {
        return maiorDeTodos;
    }

    public Double getMenorLance() {
        return menorDeTodos;
    }
}
