package com.gestion.filmotheque.service;

import java.util.List;
import com.gestion.filmotheque.entities.Categorie;

public interface IServiceCategorie {
    public Categorie createCategorie(Categorie c);
    public Categorie findCategorieById(int id);
    public Categorie findCategorieByNom(String nom);
    public List<Categorie> findAllCategories();
    public Categorie updateCategorie(Categorie c);
    public void deleteCategorie(int id);
}
