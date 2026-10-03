package com.gestion.filmotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.filmotheque.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Categorie findByNom(String nom);
}
