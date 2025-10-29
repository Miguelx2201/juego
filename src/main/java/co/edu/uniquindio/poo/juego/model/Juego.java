package co.edu.uniquindio.poo.juego.model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Equipo equipo1;
    private Equipo equipo2;

    // control de turno por jugador
    private List<Jugador> turnoJugadores; // todos los jugadores de ambos equipos
    private int indiceTurno = 0;

    public Juego(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;

        // inicializamos la lista de turnos
        turnoJugadores = new ArrayList<>();
        turnoJugadores.addAll(equipo1.getJugadores());
        turnoJugadores.addAll(equipo2.getJugadores());
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
     * Devuelve el jugador cuyo turno es actualmente activo
     */
    public Jugador obtenerJugadorActual() {
        if (turnoJugadores.isEmpty())
            return null;

        Jugador actual = turnoJugadores.get(indiceTurno);

        // si está derrotado, avanzamos automáticamente
        if (actual.estaDerrotado()) {
            siguienteTurno();
            return obtenerJugadorActual();
        }

        return actual;
    }

    /**
     * Avanza al siguiente jugador vivo
     */
    public String siguienteTurno() {
        if (hayGanador()) {
            Equipo equipoGanador = getGanador();
            return "El equipo ganador es " + equipoGanador.getNombre() + "!";
        }

        // avanzamos al siguiente jugador
        do {
            indiceTurno = (indiceTurno + 1) % turnoJugadores.size();
        } while (turnoJugadores.get(indiceTurno).estaDerrotado());

        return null;
    }

    /**
     * Comprueba si hay un ganador
     */
    public boolean hayGanador() {
        boolean eq1Vivo = equipo1.getJugadores().stream().anyMatch(j -> !j.estaDerrotado());
        boolean eq2Vivo = equipo2.getJugadores().stream().anyMatch(j -> !j.estaDerrotado());
        return !(eq1Vivo && eq2Vivo);
    }

    /**
     * Devuelve el equipo ganador
     */
    public Equipo getGanador() {
        if (!hayGanador()) return null;

        boolean eq1Vivo = equipo1.getJugadores().stream().anyMatch(j -> !j.estaDerrotado());
        return eq1Vivo ? equipo1 : equipo2;
    }
}
