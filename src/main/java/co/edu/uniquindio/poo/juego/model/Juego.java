package co.edu.uniquindio.poo.juego.model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Equipo equipo1;
    private Equipo equipo2;
    private boolean turnoEquipo1 = true;

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
        return equipo1.todosDerrotados() || equipo2.todosDerrotados();
    }
    public Equipo getGanador() {
        if(hayGanador()) {
            return equipo1.todosDerrotados() ? equipo2 : equipo1;
        }
        return null;
    }
    // Indica si es el turno del equipo 1
    //private boolean turnoEquipo1 = true;
    private int indiceJugadorActual = 0;

    /**
     * Obtiene el jugador actual que debe realizar su acción.
     */
    public Jugador obtenerJugadorActual() {
        Equipo equipoActual = turnoEquipo1 ? equipo1 : equipo2;

        // Evitar índice fuera de rango
        if (equipoActual.getJugadores().isEmpty()) return null;

        // Saltar jugadores derrotados
        while (indiceJugadorActual < equipoActual.getJugadores().size() &&
                equipoActual.getJugadores().get(indiceJugadorActual).estaDerrotado()) {
            indiceJugadorActual++;
        }

        // Si todos los jugadores de este equipo están derrotados, pasar turno
        if (indiceJugadorActual >= equipoActual.getJugadores().size()) {
            siguienteTurno();
            return obtenerJugadorActual();
        }

        return equipoActual.getJugadores().get(indiceJugadorActual);
    }

    /**
     * Pasa al siguiente turno.
     */
    public void siguienteTurno() {
        indiceJugadorActual++;
        Equipo equipoActual = turnoEquipo1 ? equipo1 : equipo2;

        // Si ya pasaron todos los jugadores del equipo actual, cambiar turno
        if (indiceJugadorActual >= equipoActual.getJugadores().size()) {
            indiceJugadorActual = 0;
            turnoEquipo1 = !turnoEquipo1;
        }
    }


}
