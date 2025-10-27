package co.edu.uniquindio.poo.juego.model;

public class Mama extends Jugador{
    public Mama(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        super(equipo, nombre, vida, ataque, defensa);
    }

    @Override
    public void atacar(Jugador jugador) {
        double danio = calcularDanio(getAtaque());
        jugador.recibirDanio(danio);
        System.out.println("La mama "+getNombre()+", le ha pegado 3 calvazos a "+jugador.getNombre()+" y le ha " +
                "inflingido "+danio+" de daño!");
    }
}
