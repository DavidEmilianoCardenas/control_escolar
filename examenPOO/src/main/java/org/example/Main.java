package org.example;

import model.Entregable;
import model.Serie;
import model.Videojuego;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Serie serie [] = new Serie[3];
        Videojuego vid [] = new Videojuego[3];

        ArrayList<Entregable> datos = new ArrayList<Entregable>();


        serie [0] = new Serie ("Pepe in the Lake", "Pepe Gutierrez");
        serie [1] = new Serie ();
        serie [2] = new Serie ("When is 5", (byte) 5,"Comedia","Paco Sanz");

        vid [0] = new Videojuego();
        vid [1] = new Videojuego("Baldur's Gate 3",(byte)80,"RPG","Larian Studios");
        vid [2] = new Videojuego("The Last of Us",(byte)60);

        serie[2].entregar();
        serie[0].entregar();
        vid[1].entregar();
        System.out.println("\nSerie con más temporadas: " + serieMasLarga(serie).getTitulo() + " - (" + serieMasLarga(serie).getCantTemp() + " temporadas)");



        datos.add(serie[0]);
        datos.add(serie[2]);
        datos.add(vid[1]);



        System.out.println("Estado de entregables:");
        for (Entregable e: datos) {
            System.out.println(e.imprimir());
        }

        System.out.println(cantEntregados(serie,vid));
        System.out.println(serieMasLarga(serie));
        System.out.println(vidMasLargo(vid));
    }

    public static int cantEntregados(Serie serie[], Videojuego vid[]) {
        int contador = 0;
        for (Serie s: serie) {
            if (s.isEntregado()) {
             contador++;
            }
        }
        for (Videojuego v: vid) {
            if (v.isEntregado()) {
                contador++;
            }
        }
        return contador;
    }

    public static String vidMasLargo (Videojuego vid[]) {
        Videojuego masLargo = null;
        for (Videojuego v : vid) {
            if (masLargo == null) {
                masLargo = v;
            }
            if (v.getHorasAprox() > masLargo.getHorasAprox()) {
                masLargo = v;
            }
        }
        return masLargo.toString();
    }

    public static Serie serieMasLarga (Serie serie[]) {
        Serie masLarga = null;
        for (Serie s : serie) {
            if (masLarga == null) {
                masLarga = s;
            }
            if (s.getCantTemp() > masLarga.getCantTemp()) {
                masLarga = s;
            }
        }
        return masLarga;
    }

}

