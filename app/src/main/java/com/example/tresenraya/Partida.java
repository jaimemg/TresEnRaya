package com.example.tresenraya;

import java.util.Random;

public class Partida {

    public final int dificultad;
    public int jugador;
    private int[] ocupadas;
    private final int [][] COMBINACIONES = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Partida (int dificultad){

        this.dificultad = dificultad;
        jugador = 1;
        ocupadas = new int[9];
        for(int i = 0; i < 9 ; i++)
            ocupadas[i] = 0;
    }

    public int iA(){

        int casilla;
        casilla = dosEnRaya(2);

        if (casilla != -1)
            return -1;

        if(dificultad > 0){

            casilla = dosEnRaya(1);
            if (casilla != -1)
                return casilla;
        }
        if (dificultad == 2)    //si no existe una casilla clave, comprobamos si las esquinas están libres y marcamos una de ellas
        {
            if (ocupadas[4] == 0) //marcamos la casilla centrar si está libre
                return 4;
            if (ocupadas[0] == 0)
                return 0;
            if (ocupadas[2] == 0)
                return 2;
            if (ocupadas[6] == 0)
                return 6;
            if (ocupadas[8] == 0)
                return 8;
        }

        Random random = new Random();       //solo marca una posición aleatoria si el código anterior no ha marcado casilla o si está en dificultad fácil
        casilla = random.nextInt(9);
        return casilla;

    }

    public int turno(){

        boolean ultMovimiento = true;
        boolean empate = true;

        for (int i = 0; i < COMBINACIONES.length ; i++ ) {

            for (int pos : COMBINACIONES[i]) {

                System.out.println("Valor en posición " +pos + " " + ocupadas[pos]);

                if(ocupadas[pos] != jugador)    //si las 3 casillas que consiguen una combinacion no tienen el mismo valor, no se ha realizado el último movimiento de la partida, es decir, no se ha ganado
                    ultMovimiento = false;

                if (ocupadas[pos] == 0) {    //cuando hay casillas vacías no puede haber un empate
                    empate = false;
                }
            }
            System.out.println("-----------------------------------------------");

            if(ultMovimiento)   //devuelve el jugador que ha ganado, que es quien realiza el último movimiento
                return jugador;

            ultMovimiento = true;
        }
        if(empate)  // se produce un empate en la partida
            return 3;


        cambiarJugador();

        return 0;
    }

    public void cambiarJugador() {
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

    public int dosEnRaya (int jugador){     //método que comprueba si existe alguna casilla que corte las 3 en raya al rival o que nos posibilite 3 en raya

        int casillaClave = -1;
        int contador = 0;

        for (int i = 0; i < COMBINACIONES.length ; i++ ) {

            for (int pos : COMBINACIONES[i]) {

                if (ocupadas[pos] == jugador)  //si la casilla está ocupada aumentamos el contador
                    contador++;

                if (ocupadas[pos] == 0)     //anotamos la casilla que está vacía, que puede ser clave
                    casillaClave = pos;
                }
            if(contador ==2 && casillaClave!= -1)   //comprobamos si es clave para conseguir un tres en raya
                return casillaClave;

            casillaClave = -1;
            contador = 0;
            }

        return -1;
        }
    }


