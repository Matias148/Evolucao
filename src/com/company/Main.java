package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Ambiente ambiente = new Ambiente(100);

        ambiente.imprimirDados();
        for (int i = 0;i<10;i++){
            Individuo indi = new Individuo();
            //System.out.println(indi.getForca());
            ambiente.add(indi);
        }

        ambiente.start();

        while (true) {
            if (ambiente.size() <= 1){
                ambiente.imprimirIndividuos();
                break;
            }
            //ambiente.imprimirIndividuos();
            Thread.sleep(1000);
        }
    }
}
