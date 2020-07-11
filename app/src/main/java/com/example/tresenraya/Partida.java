package com.example.tresenraya;

import java.util.Random;

public class Partida {

    public final int dificultad;
    public int jugador;
    private int[] ocupadas;

    public Partida(int dificultad){

        this.dificultad = dificultad;
        jugador = 1;
        ocupadas = new int[9];
        for(int i = 0; i < 9 ; i++)
            ocupadas[i] = 0;
    }

    public int iA(){

        int casilla;
        Random random = new Random();
        casilla = random.nextInt(9);
        return casilla;

    }

    public void turno(){

        jugador++;

        if(jugador > 2)
            jugador = 1;

    }

    public boolean isOcupada(int casilla){
        if(ocupadas[casilla] != 0)
            return false;
        else    ocupadas[casilla] = jugador;    //si no está ocupada, marcamos la casilla ocmo ocupada

        return true;
    }


}
