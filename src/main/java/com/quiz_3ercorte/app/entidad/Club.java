package com.quiz_3ercorte.app.entidad;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToOne
    private Entrenadores entrenador;

    @ManyToOne
    private Asociaciones asociaciones;

    @ManyToMany
    private List<Competiciones> competiciones;

    @OneToMany(mappedBy = "club")
    private List<Jugadores> jugadores;
    
    public Club() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Entrenadores getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenadores entrenador) {
        this.entrenador = entrenador;
    }

    public Asociaciones getAsociaciones() {
        return asociaciones;
    }

    public void setAsociaciones(Asociaciones asociaciones) {
        this.asociaciones = asociaciones;
    }

    public List<Competiciones> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Competiciones> competiciones) {
        this.competiciones = competiciones;
    }
    
    public List<Jugadores> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.jugadores = jugadores;
    }
}