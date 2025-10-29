package co.edu.uniquindio.poo.juego.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import co.edu.uniquindio.poo.juego.model.*;

import java.util.HashMap;
import java.util.Map;

public class CombateAppState extends SubScene {

    private final Juego juego;
    private final Equipo equipo1;
    private final Equipo equipo2;
    private final Map<Jugador, Rectangle> sprites = new HashMap<>();
    private final Map<Jugador, Label> etiquetasVida = new HashMap<>();

    private Label lblTurno;

    public CombateAppState(Equipo eq1, Equipo eq2) {
        this.equipo1 = eq1;
        this.equipo2 = eq2;
        this.juego = new Juego(eq1, eq2);
        construirInterfaz();
        actualizarTurno();
    }

    private void construirInterfaz() {
        BorderPane root = new BorderPane();
        root.setPrefSize(1280, 720);
        root.getStyleClass().add("background-combate");

        lblTurno = new Label();
        lblTurno.setFont(Font.font(28));
        lblTurno.setTextFill(Color.WHITE);

        HBox equiposContainer = new HBox(100);
        equiposContainer.setAlignment(Pos.CENTER);

        VBox boxIzquierda = crearColumnasEquipo(equipo1, Color.CORNFLOWERBLUE);
        VBox boxDerecha = crearColumnasEquipo(equipo2, Color.SALMON);

        equiposContainer.getChildren().addAll(boxIzquierda, boxDerecha);

        VBox topBox = new VBox(lblTurno);
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(10);

        root.setTop(topBox);
        root.setCenter(equiposContainer);

        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().addUINode(root);

    }

    private VBox crearColumnasEquipo(Equipo equipo, Color colorBase) {
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);

        for (Jugador jugador : equipo.getJugadores()) {
            Rectangle sprite = new Rectangle(100, 100, colorBase);
            sprite.setArcWidth(15);
            sprite.setArcHeight(15);
            sprite.setStroke(Color.BLACK);

            Label lbl = new Label(jugador.getNombre() + "\nVida: " + jugador.getVida());
            lbl.setFont(Font.font(14));
            lbl.setTextFill(Color.WHITE);
            lbl.setAlignment(Pos.CENTER);

            VBox contenedor = new VBox(sprite, lbl);
            contenedor.setAlignment(Pos.CENTER);
            contenedor.setSpacing(5);

            sprites.put(jugador, sprite);
            etiquetasVida.put(jugador, lbl);

            // Si es enemigo, puede ser clicado para ser atacado
            sprite.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    manejarAtaque(jugador);
                }
            });

            box.getChildren().add(contenedor);
        }

        return box;
    }

    private void manejarAtaque(Jugador objetivo) {
        Jugador atacante = juego.obtenerJugadorActual();

        if (atacante == null || atacante.getEquipo() == objetivo.getEquipo()) return;
        if (objetivo.estaDerrotado()) return;

        atacante.atacar(objetivo);
        actualizarVida(objetivo);
        animarAtaque(sprites.get(atacante));

        if (objetivo.estaDerrotado()) {
            sprites.get(objetivo).setFill(Color.DARKGRAY);
        }

        if (juego.hayGanador()) {
            Equipo ganador = juego.getGanador();
            lblTurno.setText("¡" + ganador.getNombre() + " ha ganado la batalla!");
            return;
        }

        juego.siguienteTurno();
        actualizarTurno();
    }

    private void animarAtaque(Rectangle sprite) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.2), sprite);
        st.setByX(0.2);
        st.setByY(0.2);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }

    private void actualizarTurno() {
        Jugador actual = juego.obtenerJugadorActual();
        if (actual != null)
            lblTurno.setText("Turno de: " + actual.getNombre() + " (" + actual.getEquipo().getNombre() + ")");
    }

    private void actualizarVida(Jugador jugador) {
        Label lbl = etiquetasVida.get(jugador);
        lbl.setText(jugador.getNombre() + "\nVida: " + jugador.getVida());
    }
}
