package co.edu.uniquindio.poo.juego.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;

    public Equipo(String nombre) {
        if(nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Metodo para añadir a un jugador verificando que este primero no exista y que no sea null
     * @param jugador
     */
    public void agregarJugador(Jugador jugador) {
        if(jugador == null) throw new IllegalArgumentException("El jugador a añadir no puede ser nulo");
        Optional<Jugador> encontrado = buscarJugador(jugador.getNombre());
        if(encontrado.isPresent()) {
            throw new IllegalArgumentException("El jugador ya existe");
        } else {
            this.jugadores.add(jugador);
        }
    }

    /**
     * Metodo para buscar un jugador verificando que el nombre no sea nulo
      * @param nombre este parametro representa el nombre del jugador que deseamos buscar
     * @return devuelve un optional que contiene al jugador si lo encontro o retornara vacio si no lo encontro
     */
    public Optional<Jugador> buscarJugador(String nombre){
        if(nombre.isBlank()) throw new IllegalArgumentException("El nombre para buscar un jugador es obligatorio");
        return jugadores.stream().filter(j -> j.getNombre().equals(nombre)).findFirst();
    }

    /**
     * Metodo para eliminar a un jugador verificando primero que el jugador exista y que el jugador a eliminar no sea
     * nulo
     * @param nombre Este parametro representa el nombre del jugador que deseamos eliminar
     */
    public void deleteJugador(String nombre){
        if(nombre.isBlank()) throw new IllegalArgumentException("El nombre del jugador a eliminar es obligatorio");
        Optional<Jugador> encontrado = buscarJugador(nombre);
        if(encontrado.isPresent()) {
            jugadores.remove(encontrado.get());
        } else {
            throw new IllegalArgumentException("El jugador no existe");
        }
    }

    /**
     * Metodo que sirve para saber si todos los jugadores de un equipo estan derrotados
     * @return
     */
    public boolean todosDerrotados(){
        for (Jugador j : jugadores) {
            if (j.isVivo()) {
                return false;
            }
        }
        return true;
    }

    public void turno(Equipo equipoRival) {

    }
}
