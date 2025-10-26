package co.edu.uniquindio.poo.juego.model;

public abstract class Jugador implements Atacante{
    private Equipo equipo;
    private String nombre;
    private double vida;
    private double ataque;
    private double defensa;

    public Jugador(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        this.equipo = equipo;
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }
    public abstract void atacar(Jugador jugador);
}
