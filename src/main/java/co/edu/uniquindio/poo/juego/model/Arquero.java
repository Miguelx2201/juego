package co.edu.uniquindio.poo.juego.model;

public class Arquero extends Jugador{

    public Arquero(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        super(equipo, nombre, vida, ataque, defensa);
    }

    @Override
    public void atacar(Jugador jugador) {
        double danio = jugador.calcularDanio(getAtaque());
        jugador.recibirDanio(danio);
        System.out.println("El arquero "+getNombre()+" le ha lanzado una lluvia de flechas a "+jugador.getNombre()+
                " y le ha inflingido "+danio+" de daño!");
    }
}
