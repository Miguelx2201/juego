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

    public void addJugador(Jugador jugador) {
        Optional<Jugador> encontrado = buscarJugador(jugador.getNombre());
        if(encontrado.isPresent()) {
            throw new IllegalArgumentException("El jugador ya existe");
        } else {
            this.jugadores.add(jugador);
        }
    }
    public Optional<Jugador> buscarJugador(String nombre){
        return jugadores.stream().filter(j -> j.getNombre().equals(nombre)).findFirst();
    }
    public void deleteJugador(String nombre){
        Optional<Jugador> encontrado = buscarJugador(nombre);
        if(encontrado.isPresent()) {
            jugadores.remove(encontrado.get());
        } else {
            throw new IllegalArgumentException("El jugador no existe");
        }
    }
    public boolean todosDerrotados(){
        for (Jugador j : jugadores) {
            if (j.isVivo()) {
                return false;
            }
        }
        return true;
    }
}
