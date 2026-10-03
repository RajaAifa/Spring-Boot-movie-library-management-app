package com.gestion.filmotheque.repository;

import com.gestion.filmotheque.entities.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeurRepository extends JpaRepository<Acteur, Integer> {
}
