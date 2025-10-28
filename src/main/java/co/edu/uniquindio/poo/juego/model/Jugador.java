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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }
    public void recibirDanio(double danio) {
        vida -= danio;
    }
    public double calcularDanio(double ataque) {
        if(defensa == 0) {
            return ataque;
        }
        else if(ataque*0.3 > defensa) {
            double diferencia = Math.abs(defensa - ataque);
            setDefensa(0);
            return ataque - diferencia;
        }
        else {
            defensa-= ataque*0.3;
            return ataque*0.7;
        }
    }
    public boolean isVivo(){
        return vida > 0;
    }
}
