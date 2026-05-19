package com.quiz_3ercorte.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz_3ercorte.app.entidad.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

}