package com.gestion.filmotheque.repository;

import com.gestion.filmotheque.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.filmotheque.entities.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    List<Film> findByTitreContaining(String name);
    List<Film> findByCategorie(Categorie categorie);
}