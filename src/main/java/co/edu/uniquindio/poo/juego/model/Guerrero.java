package co.edu.uniquindio.poo.juego.model;

public class Guerrero extends Jugador{
    public Guerrero(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        super(equipo, nombre, vida, ataque, defensa);
    }

    @Override
    public void atacar(Jugador jugador) {
        double danio = calcularDanio(getAtaque());
        jugador.recibirDanio(danio);
        System.out.println("El guerrero "+getNombre()+", le ha clavado su espada a "+jugador.getNombre()+" y le ha " +
                "inflingido "+danio+" de daño!");
    }
}
