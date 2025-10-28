package co.edu.uniquindio.poo.juego.model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private String nombre;
    private Equipo equipo1;
    private Equipo equipo2;
    private boolean turnoEquipo1 = true;

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

    /**
     * Este metodo ejecuta el juego sin embargo, investigando me di cuenta de que al ejecutarlo todo en un solo ciclo
     * While no iba a funcionar correctamente con la interactivdad que deseamos con la GUI, asi que decidimos
     * cambiarlo debido a esto, sin embargo este metodo funciona correctamente para desarrollar el juego en consola.
     * @return
     */
    public String jugar(){
        while(!(equipo1.todosDerrotados() || equipo2.todosDerrotados())){
            equipo1.turno(equipo2);
            equipo2.turno(equipo1);
        }
        Equipo equipoGanador = equipo1.todosDerrotados() ? equipo2 : equipo1;
        return "El equipo ganador es "+equipoGanador.getNombre()+"!";
    }

    /**
     * Este metodo ejecuta el juego turno por turno para permitirnos asociar este metodo con un boton de la GUI y que
     * asi el juego se vaya desarrollando segun el usuario interactue con la GUI, esta basado en la descomposicion
     * del metodo anterior jugar() en la ejecucion de un metodo indiviual para cada turno.
     * @return El metodo retorna un mensaje cuando algun equipo haya ganado al equipo rival, es decir que haya
     * derrotado a todos sus contrincantes.
     */
    public String siguienteTurno() {
        if(equipo1.todosDerrotados() || equipo2.todosDerrotados()) {
            Equipo equipoGanador = equipo1.todosDerrotados() ? equipo2 : equipo1;
            return "El equipo ganador es "+equipoGanador.getNombre()+"!";
        }
        if(turnoEquipo1) {
            equipo1.turno(equipo2);
        } else {
            equipo2.turno(equipo1);
        }
        turnoEquipo1 = !turnoEquipo1;
        return null;
    }
    //probando
}
