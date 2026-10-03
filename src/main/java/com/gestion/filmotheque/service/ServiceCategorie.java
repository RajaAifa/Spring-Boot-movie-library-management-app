package com.gestion.filmotheque.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.gestion.filmotheque.entities.Categorie;
import com.gestion.filmotheque.repository.CategorieRepository;

@Service

public class ServiceCategorie implements IServiceCategorie {

    public ServiceCategorie(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    CategorieRepository categorieRepository;

    @Override
    public Categorie createCategorie(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public Categorie findCategorieById(int id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public Categorie findCategorieByNom(String nom) {
        return categorieRepository.findByNom(nom);
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie updateCategorie(Categorie c) {
        return categorieRepository.save(c);
    }

    @Override
    public void deleteCategorie(int id) {
        categorieRepository.deleteById(id);
    }
}
