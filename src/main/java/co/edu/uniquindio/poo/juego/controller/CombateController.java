package co.edu.uniquindio.poo.juego.controller;

import co.edu.uniquindio.poo.juego.model.Juego;
import co.edu.uniquindio.poo.juego.model.Jugador;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CombateController{
    @FXML
    private Label lblTurno, lblMensaje;
    @FXML private ListView<Jugador> listaEquipo1, listaEquipo2;
    @FXML private Button btnAtacar;

    private Juego juego;

    public void iniciarCombate(Juego juego) {
        this.juego = juego;
        configurarListas();
        actualizarListas();
        actualizarTurno();
    }

    private void configurarListas() {
        listaEquipo1.setCellFactory(lv -> crearCeldaJugador());
        listaEquipo2.setCellFactory(lv -> crearCeldaJugador());
    }

    private ListCell<Jugador> crearCeldaJugador() {
        return new ListCell<>() {
            private final ProgressBar barraVida = new ProgressBar();
            private final Text nombre = new Text();
            private final VBox contenedor = new VBox(nombre, barraVida);

            {
                contenedor.setSpacing(5);
                contenedor.setStyle("-fx-padding: 5;");
                barraVida.setPrefWidth(200);
            }

            @Override
            protected void updateItem(Jugador jugador, boolean empty) {
                super.updateItem(jugador, empty);

                if (empty || jugador == null) {
                    setGraphic(null);
                } else {
                    nombre.setText(jugador.getNombre() + " (" + jugador.getVida() + " ❤️)");
                    double vida = jugador.getVida();
                    double vidaMax = jugador.getVidaMaxima();
                    double porcentaje = vida / vidaMax;
                    barraVida.setProgress(porcentaje);

                    String color;
                    if (vida <= 0) {
                        color = "grey";
                        nombre.setFill(Color.GREY);
                    } else if (porcentaje > 0.7) {
                        color = "limegreen";
                        nombre.setFill(Color.WHITE);
                    } else if (porcentaje > 0.3) {
                        color = "orange";
                        nombre.setFill(Color.WHITE);
                    } else {
                        color = "red";
                        nombre.setFill(Color.WHITE);
                    }

                    barraVida.setStyle(
                            "-fx-accent: " + color + "; " +
                                    "-fx-control-inner-background: black; " +
                                    "-fx-background-radius: 10;"
                    );

                    setGraphic(contenedor);
                }
            }
        };
    }

    private void actualizarListas() {
        listaEquipo1.getItems().setAll(juego.getEquipo1().getJugadores());
        listaEquipo2.getItems().setAll(juego.getEquipo2().getJugadores());
    }

    private void actualizarTurno() {
        Jugador actual = juego.obtenerJugadorActual();
        lblTurno.setText("Turno de: " + actual.getNombre() + " (" + actual.getEquipo().getNombre() + ")");
    }

    @FXML
    public void atacar() {
        Jugador atacante, objetivo;
        if(juego.isTurnoEquipo1()){
            atacante = juego.getEquipo1().obtenerJugadorVivoAleatorio();
            objetivo = juego.getEquipo2().obtenerJugadorVivoAleatorio();
        } else {
            atacante = juego.getEquipo2().obtenerJugadorVivoAleatorio();
            objetivo = juego.getEquipo1().obtenerJugadorVivoAleatorio();
        }


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
