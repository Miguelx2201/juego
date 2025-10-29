package co.edu.uniquindio.poo.juego.model;

public class JuegoFactory {
    /**
     * Crea un jugador con los atributos asignados manualmente desde la interfaz.
     *
     * @param tipo    "guerrero", "arquero" o "mago"
     * @param equipo  Equipo al que pertenece
     * @param nombre  Nombre del jugador
     * @param vida    Valor de vida asignado por el usuario
     * @param ataque  Valor de ataque asignado por el usuario
     * @param defensa Valor de defensa asignado por el usuario
     * @return Jugador con los valores personalizados
     */
    public static Jugador crearJugador(String tipo, Equipo equipo, String nombre,
                                       double vida, double ataque, double defensa) {

        switch (tipo.toLowerCase()) {
            case "guerrero":
                return new Guerrero(equipo, nombre, vida, ataque, defensa);
            case "arquero":
                return new Arquero(equipo, nombre, vida, ataque, defensa);
            case "mago":
                return new Mago(equipo, nombre, vida, ataque, defensa);
            default:
                throw new IllegalArgumentException("Tipo de jugador desconocido: " + tipo);
        }
    }
}
