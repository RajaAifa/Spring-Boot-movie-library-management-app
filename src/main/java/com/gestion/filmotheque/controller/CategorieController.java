package com.gestion.filmotheque.controller;

import com.gestion.filmotheque.entities.Categorie;
import com.gestion.filmotheque.entities.Film;
import com.gestion.filmotheque.service.IServiceCategorie;
import com.gestion.filmotheque.service.IServiceFilm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorie/")
public class CategorieController {

    public CategorieController(IServiceCategorie iServiceCategorie, IServiceFilm iServiceFilm) {
        this.iServiceCategorie = iServiceCategorie;
        this.iServiceFilm = iServiceFilm;
    }

    IServiceCategorie iServiceCategorie;
    IServiceFilm iServiceFilm;

    @GetMapping("all")
    public String listeCategorie(Model model) {
        model.addAttribute("categories", iServiceCategorie.findAllCategories());
        return "afficheCategorie";
    }

    @GetMapping("new")
    public String afficheNewForm () {

        return "ajoutCategorie";

    }

    @PostMapping("add")
    public String add(Categorie c) {

        iServiceCategorie.createCategorie(c);
        return "redirect:/categorie/all";

    }

    @PostMapping("modifier")
    public String modifier(Categorie c) {

        iServiceCategorie.updateCategorie(c);
        return "redirect:/categorie/all";

    }

    @GetMapping("update/{id}")
    public String afficheUpdateForm (Model model, @PathVariable int id) {

        model.addAttribute("categorie", iServiceCategorie.findCategorieById(id));
        return "updateCategorie";

    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        Categorie cat = iServiceCategorie.findCategorieById(id);
        System.out.println(cat.getNom());
        for (Film film : cat.getFilms()){
            film.setCategorie(iServiceCategorie.findCategorieByNom("Sans Categorie"));
            System.out.println(film.getCategorie().getNom());
            System.out.println(iServiceFilm.updateFilm(film));
        }
        iServiceCategorie.deleteCategorie(id);
        return "redirect:/categorie/all";

    }
}
