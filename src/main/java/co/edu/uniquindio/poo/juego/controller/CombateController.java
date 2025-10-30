package co.edu.uniquindio.poo.juego.controller;

import co.edu.uniquindio.poo.juego.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CombateController {

    @FXML private Label lblTurno, lblMensaje;
    @FXML private ListView<String> listaEquipo1, listaEquipo2;
    @FXML private Button btnAtacar;

    private Juego juego;

    public void iniciarCombate(Juego juego) {
        this.juego = juego;
        actualizarListas();
        actualizarTurno();
    }

    private void actualizarListas() {
        listaEquipo1.getItems().clear();
        listaEquipo2.getItems().clear();

        juego.getEquipo1().getJugadores().forEach(j ->
                listaEquipo1.getItems().add(j.getNombre() + " - ❤️ " + j.getVida()));
        juego.getEquipo2().getJugadores().forEach(j ->
                listaEquipo2.getItems().add(j.getNombre() + " - ❤️ " + j.getVida()));
    }

    private void actualizarTurno() {
        Jugador actual = juego.obtenerJugadorActual();
        lblTurno.setText("Turno de: " + actual.getNombre() + " (" + actual.getEquipo().getNombre() + ")");
    }

    @FXML
    public void atacar() {
        Jugador atacante = juego.obtenerJugadorActual();
        Jugador objetivo = juego.isTurnoEquipo1()
                ? juego.getEquipo2().getJugadores().get(0)
                : juego.getEquipo1().getJugadores().get(0);

        atacante.atacar(objetivo);
        lblMensaje.setText(atacante.getNombre() + " atacó a " + objetivo.getNombre());

        actualizarListas();

        if (juego.hayGanador()) {
            lblMensaje.setText("🏆 " + juego.getGanador().getNombre() + " ha ganado la batalla!");
            btnAtacar.setDisable(true);
        } else {
            juego.siguienteTurno();
            actualizarTurno();
        }
    }
}
