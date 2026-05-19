package com.quiz_3ercorte.app.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz_3ercorte.app.entidad.Asociaciones;
import com.quiz_3ercorte.app.entidad.Club;
import com.quiz_3ercorte.app.entidad.Competiciones;
import com.quiz_3ercorte.app.entidad.Entrenadores;
import com.quiz_3ercorte.app.entidad.Jugadores;

import com.quiz_3ercorte.app.repositorio.AsociacionesRepository;
import com.quiz_3ercorte.app.repositorio.ClubRepository;
import com.quiz_3ercorte.app.repositorio.CompeticionesRepository;
import com.quiz_3ercorte.app.repositorio.EntrenadorRepository;
import com.quiz_3ercorte.app.repositorio.JugadoresRepository;

@Controller
public class ClubController {

    // =========================
    // REPOSITORIES
    // =========================

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private JugadoresRepository jugadorRepository;

    @Autowired
    private CompeticionesRepository competicionesRepository;

    @Autowired
    private AsociacionesRepository asociacionesRepository;

    // =========================
    // INDEX
    // =========================

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    // =========================
    // PAGINAS GET
    // =========================

    @GetMapping("/club")
    public String club(Model model) {
        model.addAttribute("entrenadores",   entrenadorRepository.findAll());
        model.addAttribute("jugadores",      jugadorRepository.findAll());
        model.addAttribute("competiciones",  competicionesRepository.findAll());
        model.addAttribute("asociaciones",   asociacionesRepository.findAll());
        return "crearClub";
    }

    @GetMapping("/entrenador")
    public String entrenador() {
        return "crearEntrenador";
    }

    @GetMapping("/jugador")
    public String jugador() {
        return "crearJugador";
    }

    @GetMapping("/competicion")
    public String competicion(Model model) {
        model.addAttribute("clubes",        clubRepository.findAll());
        model.addAttribute("entrenadores",  entrenadorRepository.findAll());
        model.addAttribute("jugadores",     jugadorRepository.findAll());
        model.addAttribute("asociaciones",  asociacionesRepository.findAll());
        return "crearCompeticion";
    }

    @GetMapping("/asociacion")
    public String asociacion() {
        return "crearAsociacion";
    }

    // =========================
    // GUARDAR ENTRENADOR
    // =========================

    @PostMapping("/guardarEntrenador")
    public String guardarEntrenador(
            @RequestParam String nombre,
            @RequestParam String apellido,
            Model model
    ) {
        Entrenadores ent = new Entrenadores();
        ent.setNombre(nombre);
        ent.setApellido(apellido);
        entrenadorRepository.save(ent);
        model.addAttribute("mensaje", "Entrenador guardado correctamente");
        return "crearEntrenador";
    }

    // =========================
    // GUARDAR JUGADOR
    // =========================

    @PostMapping("/guardarJugador")
    public String guardarJugador(
            @RequestParam String nombre,
            @RequestParam String apellido,
            Model model
    ) {
        Jugadores jug = new Jugadores();
        jug.setNombre(nombre);
        jug.setApellido(apellido);
        jugadorRepository.save(jug);
        model.addAttribute("mensaje", "Jugador guardado correctamente");
        return "crearJugador";
    }

    
    @GetMapping("/listar")
    public String listarClubes(Model model) {
        model.addAttribute("listaClubes", clubRepository.findAll()); // o tu repositorio
        return "listar"; // nombre del archivo listar.html en templates
    }
    // =========================
    // GUARDAR COMPETICION
    // =========================

    @PostMapping("/guardarCompeticion")
    public String guardarCompeticion(
            @RequestParam String nombre,
            Model model
    ) {
        Competiciones comp = new Competiciones();
        comp.setNombre(nombre);
        competicionesRepository.save(comp);

        // Recargar datos para el formulario
        model.addAttribute("clubes",       clubRepository.findAll());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("jugadores",    jugadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionesRepository.findAll());
        model.addAttribute("mensaje", "Competición guardada correctamente");
        return "crearCompeticion";
    }

    // =========================
    // GUARDAR ASOCIACION
    // =========================

    @PostMapping("/guardarAsociacion")
    public String guardarAsociacion(
            @RequestParam String nombre,
            Model model
    ) {
        Asociaciones aso = new Asociaciones();
        aso.setNombre(nombre);
        asociacionesRepository.save(aso);
        model.addAttribute("mensaje", "Asociación guardada correctamente");
        return "crearAsociacion";
    }

    // =========================
    // GUARDAR CLUB
    // =========================

    @PostMapping("/guardarClubFinal")
    public String guardarClubFinal(
            @RequestParam String club,
            @RequestParam Long entrenador,
            @RequestParam Long jugador,
            @RequestParam Long competicion,
            @RequestParam Long asociacion,
            Model model
    ) {
        Entrenadores ent  = entrenadorRepository.findById(entrenador).orElse(null);
        Jugadores    jug  = jugadorRepository.findById(jugador).orElse(null);
        Competiciones comp = competicionesRepository.findById(competicion).orElse(null);
        Asociaciones  aso  = asociacionesRepository.findById(asociacion).orElse(null);

        Club nuevoClub = new Club();
        nuevoClub.setNombre(club);
        nuevoClub.setEntrenador(ent);
        nuevoClub.setAsociaciones(aso);

        List<Competiciones> lista = new ArrayList<>();
        lista.add(comp);
        nuevoClub.setCompeticiones(lista);

        clubRepository.save(nuevoClub);

        if (jug != null) {
            jug.setClub(nuevoClub);
            jugadorRepository.save(jug);
        }

        model.addAttribute("listaClubes", clubRepository.findAll());
        return "listar";
    }
}