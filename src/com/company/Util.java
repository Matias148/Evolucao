package com.company;

import java.util.Random;

public class Util {

    public static int gerarRandom(int max){
        Random gera = new Random();
        return gera.nextInt(max+1);
    }

    public static boolean chance(int porcento){
        return gerarRandom(100) < porcento;
    }
}
