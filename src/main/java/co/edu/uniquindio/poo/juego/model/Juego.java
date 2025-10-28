package co.edu.uniquindio.poo.juego.model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private String nombre;
    private Equipo equipo1;
    private Equipo equipo2;

    public Juego(String nombre, Equipo equipo1, Equipo equipo2) {
        this.nombre = nombre;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public void jugar(){

    }
}
