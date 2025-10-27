package co.edu.uniquindio.poo.juego.model;

public class Abuela extends Jugador{
    public Abuela(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        super(equipo, nombre, vida, ataque, defensa);
    }

    @Override
    public void atacar(Jugador jugador) {
        double danio = calcularDanio(getAtaque());
        jugador.recibirDanio(danio);
        System.out.println("La abuela "+getNombre()+", le ha pegado un chancletazo francotirador desde la distancia a "
                +jugador.getNombre()+" y le ha inflingido "+danio+" de daño!");
    }
}
