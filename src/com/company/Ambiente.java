package com.company;

import java.util.Random;

public class Ambiente extends Thread{
    private int temperatura;
    private int tempAmbiente;
    private int comidaDisponivel;
    private Individuo[] moradores;
    private static boolean alterando = false;

    public Ambiente(int populacao){
        moradores = new Individuo[populacao];
        this.tempAmbiente = Util.gerarRandom(100);
        this.temperatura = 1;
        this.comidaDisponivel = Util.gerarRandom(1000)+5000;
    }

    public void imprimirIndividuos(){
        for (int i = 0;i<size();i++){
            if (moradores[i]!=null)
            System.out.println("    força:"+moradores[i].getForca()+" tempAmbiente:"+moradores[i].getTempAmbiente()+" " +
                    "variaçao:"+moradores[i].getTempVariacao()+" tipoTemp:"+moradores[i].getTemperatura());
        }
    }

    public void imprimirDados(){
        System.out.println("temperaturaAmbiente:"+tempAmbiente+" TemperaturaTipo:"+temperatura+
                " Comida:"+comidaDisponivel+" individuos:"+size());
    }

    @Override
    public void run(){
        while (size()>15 && comidaDisponivel < 10000){
            if (!alterando) {
                imprimirDados();
                alterando = true;
                for (Individuo i : moradores) {
                    if (i != null) {
                        if (i.getComidaNecessaria() > comidaDisponivel) {
                            i.setTestesMorte(i.getTestesMorte()+1);
                            if (i.getTestesMorte() == 3 || !sobrevive(i)) {
                                remove(i);
                            }
                        } else {
                            comidaDisponivel -= i.getComidaNecessaria();
                            i.evoluir();
//                            i.setForca(i.getForca()+1);
//                            i.setComidaNecessaria(i.comida());
                        }
                    }
                    int reprodutor = Util.gerarRandom(size());
                    if (reprodutor==size() && size()>0){reprodutor--;}
                    if (moradores[reprodutor] != null && i != null) {
                        if ((i.getSexo() == 1 && moradores[reprodutor].getSexo() == 2) ||
                                (i.getSexo() == 2 && moradores[reprodutor].getSexo() == 1)) {
                            add(reproduzir(i, moradores[reprodutor]));
                        }
                    }
                }
                alterando = false;
            }
            if (Util.chance(10)){
                if (this.temperatura == 1){
                    this.temperatura = 2;
                }else{
                    this.temperatura = 1;
                }
            }
            this.tempAmbiente += aumentaTemp();
            this.comidaDisponivel += Util.gerarRandom(100);
        }
        imprimirIndividuos();
    }

    public int aumentaTemp(){
        if (Util.gerarRandom(1) == 1){
            return Util.gerarRandom(10);
        }else{
            return -Util.gerarRandom(10);
        }
    }

    public boolean sobrevive(Individuo individuo){
        if (individuo.getTemperatura() != temperatura){
            if (Util.chance(30)) {
                return true;
            }
            return false;
        }
        if (getTempAmbiente() > individuo.getTempAmbiente()+individuo.getTempVariacao()){
            return false;
        }
        if (getTempAmbiente() < individuo.getTempAmbiente()-individuo.getTempVariacao()){
            return false;
        }
        return true;
    }

    private Individuo reproduzir(Individuo pai, Individuo mae){
        return new Individuo(pai, mae);
    }

    public void add(Individuo indi){
        for (int i = 0; i < moradores.length; i++){
            if (moradores[i] == null){
                moradores[i] = indi;
                return;
            }
        }
    }

    public void remove(Individuo indi){
        for (int i = 0; i < moradores.length; i++){
            if (moradores[i] == indi){
                moradores[i] = null;
                return;
            }
        }
    }

    public int size(){
        int aux = 0;
        for (int i = 0; i < moradores.length; i++){
            if (moradores[i] != null){
                aux++;
            }
        }
        return aux;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public int getTempAmbiente() {
        return tempAmbiente;
    }

    public int getComidaDisponivel() {
        return comidaDisponivel;
    }

    public Individuo[] getMoradores() {
        return moradores;
    }
}
