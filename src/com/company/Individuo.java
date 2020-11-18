package com.company;

import java.util.Random;

public class Individuo {
    private int forca;
    private int tempAmbiente;
    private int temperatura;
    private int tempVariacao;
    private int sexo = 1;
    private int comidaNecessaria;
    private int testesMorte = 0;

    public int getTestesMorte() {
        return testesMorte;
    }

    public void setTestesMorte(int testesMorte) {
        this.testesMorte = testesMorte;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public Individuo(){
        forca = Util.gerarRandom(100);
        tempAmbiente = Util.gerarRandom(100);
        temperatura = sexo();
        tempVariacao = Util.gerarRandom(100);
        sexo = sexo();
        comidaNecessaria = comida();
    }

    public Individuo(Individuo pai, Individuo mae){
        forca = (pai.getForca()+mae.getForca())/2;
        tempAmbiente = (pai.getTempAmbiente()+mae.getTempAmbiente())/2;
        temperatura = (pai.getTemperatura());
        tempVariacao = (pai.getTempVariacao()+mae.getTempVariacao())/2;
        sexo = sexo();
        comidaNecessaria = comida();
    }

    public void evoluir(){
        forca += Util.gerarRandom(5);
        tempAmbiente += Util.gerarRandom(5);
        tempVariacao += Util.gerarRandom(5);
        comidaNecessaria = comida();
    }

    private int sexo(){
        Random gera = new Random();
        return gera.nextInt(2)+1;
    }

    public int comida(){
        int aux = forca+tempAmbiente+tempVariacao;
        return aux/3;
    }

    public void setComidaNecessaria(int comidaNecessaria) {
        this.comidaNecessaria = comidaNecessaria;
    }
    public int getForca() { return forca; }
    public int getTempAmbiente() { return tempAmbiente; }
    public int getTemperatura() { return temperatura; }
    public int getTempVariacao() { return tempVariacao; }
    public int getSexo() { return sexo; }
    public int getComidaNecessaria() { return comidaNecessaria; }
}
