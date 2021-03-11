package br.com.gams.leilao.dominio;

public class AnoBissexto {

    private Integer year;

    public boolean verifica(Integer year){
        if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) return true;
        return false;
    }
}
