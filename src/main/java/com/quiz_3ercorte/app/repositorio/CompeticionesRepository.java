package com.quiz_3ercorte.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz_3ercorte.app.entidad.Competiciones;

public interface CompeticionesRepository extends JpaRepository<Competiciones, Long> {

}