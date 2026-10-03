package com.gestion.filmotheque.service;

import com.gestion.filmotheque.entities.Acteur;

import java.util.List;

public interface IServiceActeur {
    public Acteur createActeur(Acteur f);
    public Acteur findActeurById(int id);
    public List<Acteur> findAllActeurs();
    public Acteur updateActeur(Acteur f);
    public void deleteActeur(int id);
}
