package co.edu.uniquindio.poo.juego.controller;

import co.edu.uniquindio.poo.juego.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class ConfiguracionController {

    @FXML private TextField txtNombre;
    @FXML private ComboBox<String> comboTipo;
    @FXML private TextField txtVida, txtAtaque, txtDefensa;
    @FXML private ListView<String> listaEquipo1, listaEquipo2;
    @FXML private Label lblAviso;

    private Equipo equipo1 = new Equipo("Equipo Azul");
    private Equipo equipo2 = new Equipo("Equipo Rojo");
    private boolean agregandoEquipo1 = true;

    @FXML
    public void initialize() {
        comboTipo.setItems(FXCollections.observableArrayList("Guerrero", "Arquero", "Mago", "Abuela", "Mama"));
    }

    @FXML
    public void cambiarEquipo() {
        agregandoEquipo1 = !agregandoEquipo1;
        lblAviso.setText(agregandoEquipo1 ? "Agregando al Equipo Azul" : "Agregando al Equipo Rojo");
    }

    @FXML
    public void agregarJugador() {
        try {
            String nombre = txtNombre.getText().trim();
            String tipo = comboTipo.getValue();
            double vida = Double.parseDouble(txtVida.getText());
            double ataque = Double.parseDouble(txtAtaque.getText());
            double defensa = Double.parseDouble(txtDefensa.getText());

            if (nombre.isEmpty() || tipo == null) {
                lblAviso.setText("⚠️ Debes ingresar todos los datos.");
                return;
            }

            Equipo equipoActual = agregandoEquipo1 ? equipo1 : equipo2;
            if (equipoActual.getJugadores().size() >= 5) {
                lblAviso.setText("⚠️ Este equipo ya tiene 5 jugadores.");
                return;
            }

            Jugador nuevo = switch (tipo) {
                case "Guerrero" -> new Guerrero(equipoActual, nombre, vida, ataque, defensa);
                case "Arquero" -> new Arquero(equipoActual, nombre, vida, ataque, defensa);
                case "Mago" -> new Mago(equipoActual, nombre, vida, ataque, defensa);
                case "Abuela" -> new Abuela(equipoActual, nombre, vida, ataque, defensa);
                case "Mama" -> new Mama(equipoActual, nombre, vida, ataque, defensa);
                default -> throw new IllegalStateException("Tipo inválido");
            };

            equipoActual.agregarJugador(nuevo);
            (agregandoEquipo1 ? listaEquipo1 : listaEquipo2)
                    .getItems().add(nombre + " (" + tipo + ")");
            lblAviso.setText("Jugador agregado correctamente.");

        } catch (NumberFormatException e) {
            lblAviso.setText("⚠️ Los stats deben ser numéricos.");
        }
    }

    @FXML
    public void iniciarBatalla() throws IOException {
        if (equipo1.getJugadores().size() < 5 || equipo2.getJugadores().size() < 5) {
            lblAviso.setText("⚠️ Ambos equipos deben tener 5 jugadores.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/juego/CombateView.fxml"));
        Parent root = loader.load();
        CombateController combateController = loader.getController();
        combateController.iniciarCombate(new Juego(equipo1, equipo2));

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
