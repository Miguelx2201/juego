package co.edu.uniquindio.poo.juego.model;

public class Mago extends Jugador {
    public Mago(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        super(equipo, nombre, vida, ataque, defensa);
    }

    @Override
    public void atacar(Jugador jugador) {
        double danio = jugador.calcularDanio(getAtaque());
        jugador.recibirDanio(danio);
        System.out.println("El mago "+getNombre()+" le ha lanzado una bola de fuego a "+jugador.getNombre()+" y le ha" +
                " inflingido "+danio+" de daño!");
    }
}
