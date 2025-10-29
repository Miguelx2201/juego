package co.edu.uniquindio.poo.juego.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import co.edu.uniquindio.poo.juego.model.*;

public class ConfiguracionAppState extends SubScene {

    private Equipo equipo1 = new Equipo("Equipo Azul");
    private Equipo equipo2 = new Equipo("Equipo Rojo");
    private VBox boxEquipo1, boxEquipo2;
    private Label lblAviso;

    public ConfiguracionAppState() {
        construirInterfaz();
    }

    private void construirInterfaz() {
        BorderPane root = new BorderPane();
        root.setPrefSize(1280, 720);
        root.getStyleClass().add("background-config");

        HBox equiposContainer = new HBox(50);
        equiposContainer.setAlignment(Pos.CENTER);

        // Contenedor del equipo 1
        boxEquipo1 = crearPanelEquipo(equipo1, Color.CORNFLOWERBLUE);
        // Contenedor del equipo 2
        boxEquipo2 = crearPanelEquipo(equipo2, Color.SALMON);

        equiposContainer.getChildren().addAll(boxEquipo1, boxEquipo2);

        Button btnIniciar = new Button("Iniciar Batalla");
        btnIniciar.getStyleClass().add("btn-iniciar");
        btnIniciar.setOnAction(e -> {
            if (equipo1.getJugadores().size() == 5 && equipo2.getJugadores().size() == 5) {
                CombateAppState combate = new CombateAppState(equipo1, equipo2);
                FXGL.getGameScene().clearUINodes();
                FXGL.getGameScene().addUINode(combate.getRoot());
            } else {
                lblAviso.setText("Cada equipo debe tener 5 jugadores antes de iniciar.");
            }
        });

        lblAviso = new Label("");
        lblAviso.getStyleClass().add("lbl-aviso");

        VBox bottomBox = new VBox(10, lblAviso, btnIniciar);
        bottomBox.setAlignment(Pos.CENTER);

        root.setCenter(equiposContainer);
        root.setBottom(bottomBox);

        FXGL.getGameScene().clearUINodes();
        FXGL.getGameScene().addUINode(root);
    }

    private VBox crearPanelEquipo(Equipo equipo, Color color) {
        VBox panel = new VBox(10);
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPrefWidth(400);
        panel.getStyleClass().add("panel-equipo");

        Label lblTitulo = new Label(equipo.getNombre());
        lblTitulo.setTextFill(color);
        lblTitulo.setFont(Font.font(24));

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del jugador");

        ComboBox<String> tipoCombo = new ComboBox<>();
        tipoCombo.getItems().addAll("Guerrero", "Arquero", "Mago", "Abuela", "Mama");
        tipoCombo.setPromptText("Tipo");

        TextField vidaField = new TextField();
        vidaField.setPromptText("Vida (ej. 100)");

        TextField ataqueField = new TextField();
        ataqueField.setPromptText("Ataque (ej. 25)");

        TextField defensaField = new TextField();
        defensaField.setPromptText("Defensa (ej. 10)");

        Button btnAgregar = new Button("Agregar jugador");
        btnAgregar.getStyleClass().add("btn-agregar");

        ListView<String> listaJugadores = new ListView<>();
        listaJugadores.setPrefHeight(300);

        btnAgregar.setOnAction(e -> {
            String nombre = nombreField.getText().trim();
            String tipo = tipoCombo.getValue();
            double vida, ataque, defensa;

            try {
                vida = Double.parseDouble(vidaField.getText());
                ataque = Double.parseDouble(ataqueField.getText());
                defensa = Double.parseDouble(defensaField.getText());
            } catch (NumberFormatException ex) {
                lblAviso.setText("Ingresa valores numéricos válidos para los atributos.");
                return;
            }

            if (nombre.isEmpty() || tipo == null) {
                lblAviso.setText("Completa el nombre y tipo del jugador.");
                return;
            }

            if (equipo.getJugadores().size() >= 5) {
                lblAviso.setText("Máximo 5 jugadores por equipo.");
                return;
            }

            Jugador jugador = switch (tipo) {
                case "Guerrero" -> new Guerrero(equipo, nombre, vida, ataque, defensa);
                case "Arquero" -> new Arquero(equipo, nombre, vida, ataque, defensa);
                case "Mago" -> new Mago(equipo, nombre, vida, ataque, defensa);
                case "Abuela" -> new Abuela(equipo, nombre, vida, ataque, defensa);
                case "Mama" -> new Mama(equipo, nombre, vida, ataque, defensa);
                default -> null;
            };

            if (jugador != null) {
                equipo.agregarJugador(jugador);
                listaJugadores.getItems().add(nombre + " (" + tipo + ") Vida:" + vida + " Atk:" + ataque + " Def:" + defensa);
                nombreField.clear();
                tipoCombo.setValue(null);
                vidaField.clear();
                ataqueField.clear();
                defensaField.clear();
                lblAviso.setText("");
            }
        });

        panel.getChildren().addAll(
                lblTitulo,
                nombreField,
                tipoCombo,
                vidaField,
                ataqueField,
                defensaField,
                btnAgregar,
                listaJugadores
        );
        return panel;
    }
}
