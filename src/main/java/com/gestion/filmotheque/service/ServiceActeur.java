package com.gestion.filmotheque.service;

import com.gestion.filmotheque.entities.Acteur;
import com.gestion.filmotheque.repository.ActeurRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceActeur implements IServiceActeur {

    public ServiceActeur(ActeurRepository acteurRepository) {
        this.acteurRepository = acteurRepository;
    }

    ActeurRepository acteurRepository;

    @Override
    public Acteur createActeur(Acteur c) {
        return acteurRepository.save(c);
    }

    @Override
    public Acteur findActeurById(int id) {
        return acteurRepository.findById(id).get();
    }

    @Override
    public List<Acteur> findAllActeurs() {
        return acteurRepository.findAll();
    }

    @Override
    public Acteur updateActeur(Acteur c) {
        return acteurRepository.save(c);
    }

    @Override
    public void deleteActeur(int id) {
        acteurRepository.deleteById(id);
    }
}

