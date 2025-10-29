package co.edu.uniquindio.poo.juego.app;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import co.edu.uniquindio.poo.juego.controller.ConfiguracionAppState;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static javafx.application.Application.launch;

public class App extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Batalla de Equipos");
        settings.setVersion("1.0");
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setMainMenuEnabled(false);
        settings.setGameMenuEnabled(false);
        settings.setFullScreenAllowed(false);
    }

    @Override
    protected void initGame() {
        // Iniciamos en la pantalla de configuración
        FXGL.getGameScene().getRoot().getStylesheets().add(
                getClass().getResource("/co/edu/uniquindio/poo/juego/view/styles.css").toExternalForm()
        );

        getGameWorld().addEntityFactory(null);
        getGameScene().clearUINodes();
        getGameScene().addUINode(new ConfiguracionAppState().getRoot());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
