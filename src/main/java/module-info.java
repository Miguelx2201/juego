module co.edu.uniquindio.poo.juego {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.juego to javafx.fxml;
    exports co.edu.uniquindio.poo.juego;
    exports co.edu.uniquindio.poo.juego.controller;
    opens co.edu.uniquindio.poo.juego.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.juego.app;
    opens co.edu.uniquindio.poo.juego.app to javafx.fxml;
}