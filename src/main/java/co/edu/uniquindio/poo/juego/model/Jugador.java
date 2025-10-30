package co.edu.uniquindio.poo.juego.model;

public abstract class Jugador implements Atacante{
    private Equipo equipo;
    private String nombre;
    private double vida;
    private double ataque;
    private double defensa;
    private double vidaMaxima;

    public Jugador(Equipo equipo, String nombre, double vida, double ataque, double defensa) {
        this.equipo = equipo;
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
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

    public double getVidaMaxima() {
        return vidaMaxima;
    }

    /**
     * Metodo para recibir el daño, restandolo de la vida actual
     * @param danio
     */
    public void recibirDanio(double danio) {
        vida -= danio;
        if(vida <= 0) {
            vida = 0;
        }
    }

    /**
     * Metodo para calcular el daño que se le realizara al jugador, para el calculo se tiene en cuenta la defensa, en
     * caso de que el jugador tenga defensa, esta absorbera el 30% del daño potencial que tiene el ataque del rival
     * @param ataque es el valor del ataque del jugador
     * @return
     */
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

    /**
     * Metodo para saber si un jugador esta vivo o no
     * @return
     */
    public boolean isVivo(){
        return vida > 0;
    }
    public boolean estaDerrotado() {
        return !isVivo();
    }
}
