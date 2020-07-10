package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

    private int jugadores;
    private int[] casillas;
    private Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializar el array de casillas
        casillas = new int[9];
        casillas[0] = R.id.a1;
        casillas[1] = R.id.a2;
        casillas[2] = R.id.a3;
        casillas[3] = R.id.b1;
        casillas[4] = R.id.b2;
        casillas[5] = R.id.b3;
        casillas[6] = R.id.c1;
        casillas[7] = R.id.c2;
        casillas[8] = R.id.c3;

    }

    public void aJugar(View view){
        ImageView imagen;
            for(int cadaCasilla: casillas){
                imagen = (ImageView)findViewById((cadaCasilla)); //obtenemos cada casilla
                imagen.setImageResource(R.drawable.casilla); //reseteamos cada casilla para que esté vacía
            }
        jugadores = 1; //suponemos que ha seleccionado el boton de 1 jugadores
            if(view.getId() == R.id.dosjug) //si ha pinchado el boton de 2 jugadores esta condición se cumplirá,
                jugadores = 2;              // ya que la vista que pasamos por parámetro será el botón de 2 jugadores


        RadioGroup configDificultad = (RadioGroup)findViewById(R.id.configDificultad);
        int id = configDificultad.getCheckedRadioButtonId();    //obtenemos la dificultad seleccionada
        int dificultad = 0; //suponemos que ha seleccionado la dificultad fácil

            if(id == R.id.normal)          //cambiamos la dificultad si es necesario
                dificultad = 1;
            else if (id == R.id.dificil)
                dificultad = 2;


        partida = new Partida(dificultad);
        ((Button)findViewById(R.id.unjug)).setEnabled(false);       //inhabilitamos los botones durante la partida
        ((Button)findViewById(R.id.dosjug)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configDificultad)).setAlpha(0);

    }
}