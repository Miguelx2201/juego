module co.edu.uniquindio.poo.juego {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.juego to javafx.fxml;
    exports co.edu.uniquindio.poo.juego;
}