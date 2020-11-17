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
        this.tempAmbiente = gerarRandom(100);
        this.temperatura = 1;
        this.comidaDisponivel = gerarRandom(1000);
    }

    public void imprimirIndividuos(){
        for (int i = 0;i<size();i++){
            if (moradores[i]!=null)
            System.out.println("    "+moradores[i].getForca());
        }
    }

    public void imprimirDados(){
        System.out.println("temperatura Ambiente:"+tempAmbiente+" Temperatura Tipo:"+temperatura+
                " Comida:"+comidaDisponivel+" individuos:"+size());
    }

    @Override
    public void run(){
        while (size()>0 && comidaDisponivel < 10000){
            if (!alterando) {
                alterando = true;
                for (Individuo i : moradores) {
                    if (i != null) {
                        if (i.getComidaNecessaria() > comidaDisponivel) {
                            remove(i);
                        } else {
                            comidaDisponivel -= i.getComidaNecessaria();
                            i.setForca(i.getForca()+1);
                            i.setComidaNecessaria(i.comida());
                        }
                    }
                    int reprodutor = gerarRandom(size());
                    if (moradores[reprodutor] != null && i != null) {
                        if ((i.getSexo() == 1 && moradores[reprodutor].getSexo() == 2) ||
                                (i.getSexo() == 2 && moradores[reprodutor].getSexo() == 1)) {
                            add(reproduzir(i, moradores[reprodutor]));
                        }
                    }
                }
                alterando = false;
            }
            this.comidaDisponivel += gerarRandom(100);
            imprimirDados();
        }
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

    private int gerarRandom(int max){
        Random gera = new Random();
        return gera.nextInt(max+1);
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
