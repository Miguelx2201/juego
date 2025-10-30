package co.edu.uniquindio.poo.juego.model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Equipo equipo1;
    private Equipo equipo2;
    private boolean turnoEquipo1 = true;
    private int indiceJugadorActualEquipo1 = 0;
    private int indiceJugadorActualEquipo2 = 0;

    public Juego( Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
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

    public boolean isTurnoEquipo1() {
        return turnoEquipo1;
    }

    public void setTurnoEquipo1(boolean turnoEquipo1) {
        this.turnoEquipo1 = turnoEquipo1;
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
//    public String siguienteTurno() {
//        if(equipo1.todosDerrotados() || equipo2.todosDerrotados()) {
//            Equipo equipoGanador = equipo1.todosDerrotados() ? equipo2 : equipo1;
//            return "El equipo ganador es "+equipoGanador.getNombre()+"!";
//        }
//        if(turnoEquipo1) {
//            equipo1.turno(equipo2);
//        } else {
//            equipo2.turno(equipo1);
//        }
//        turnoEquipo1 = !turnoEquipo1;
//        return null;
//    }
    public boolean hayGanador() {
        boolean equipo1Muerto = equipo1.getJugadores().stream().noneMatch(j -> j.getVida() > 0);
        boolean equipo2Muerto = equipo2.getJugadores().stream().noneMatch(j -> j.getVida() > 0);
        return equipo1Muerto || equipo2Muerto;
    }

    public Equipo getGanador() {
        boolean equipo1Muerto = equipo1.getJugadores().stream().noneMatch(j -> j.getVida() > 0);
        boolean equipo2Muerto = equipo2.getJugadores().stream().noneMatch(j -> j.getVida() > 0);
        if (equipo1Muerto && !equipo2Muerto) return equipo2;
        if (equipo2Muerto && !equipo1Muerto) return equipo1;
        return null;
    }
    // Indica si es el turno del equipo 1
    //private boolean turnoEquipo1 = true;


    /**
     * Obtiene el jugador actual que debe realizar su acción.
     */
    public Jugador obtenerJugadorActual() {
        return turnoEquipo1
                ? equipo1.getJugadores().get(indiceJugadorActualEquipo1)
                : equipo2.getJugadores().get(indiceJugadorActualEquipo2);
    }

    /**
     * Pasa al siguiente turno.
     */
    public void siguienteTurno() {
        // Alterna el turno entre equipos cada vez que se llama
        turnoEquipo1 = !turnoEquipo1;

        if (turnoEquipo1) {
            // Si es turno del equipo 1, avanza al siguiente jugador vivo de ese equipo
            indiceJugadorActualEquipo1++;
            if (indiceJugadorActualEquipo1 >= equipo1.getJugadores().size()) {
                indiceJugadorActualEquipo1 = 0;
            }
        } else {
            // Si es turno del equipo 2, avanza al siguiente jugador vivo de ese equipo
            indiceJugadorActualEquipo2++;
            if (indiceJugadorActualEquipo2 >= equipo2.getJugadores().size()) {
                indiceJugadorActualEquipo2 = 0;
            }
        }
    }



}
