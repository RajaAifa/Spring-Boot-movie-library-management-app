package com.gestion.filmotheque.controller;

import com.gestion.filmotheque.entities.Acteur;
import com.gestion.filmotheque.entities.Film;
import com.gestion.filmotheque.service.IServiceActeur;
import com.gestion.filmotheque.service.IServiceFilm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/acteur/")
public class ActeurController {

    public ActeurController(IServiceActeur iServiceActeur, IServiceFilm iServiceFilm) {
        this.iServiceActeur = iServiceActeur;
        this.iServiceFilm = iServiceFilm;
    }

    IServiceActeur iServiceActeur;
    IServiceFilm iServiceFilm;


    @GetMapping("all")
    public String listeActeurs(Model model) {
        model.addAttribute("acteurs", iServiceActeur.findAllActeurs());
        return "afficheActeur";
    }

    @GetMapping("new")
    public String afficheNewForm (Model model) {

        model.addAttribute("films", iServiceFilm.findAllFilms());
        return "ajoutActeur";

    }

    @PostMapping("add")
    public String add(Acteur a) {

        iServiceActeur.createActeur(a);
        return "redirect:/acteur/all";

    }

    @PostMapping("modifier")
    public String modifier(Acteur a) {

        iServiceActeur.updateActeur(a);
        return "redirect:/acteur/all";

    }

    @GetMapping("update/{id}")
    public String afficheUpdateForm (Model model, @PathVariable int id) {

        model.addAttribute("acteur", iServiceActeur.findActeurById(id));
        model.addAttribute("films", iServiceFilm.findAllFilms());
        return "updateActeur";

    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {

        Acteur acteur = iServiceActeur.findActeurById(id);
        for (Film film : acteur.getFilms()) {
            film.getActeurs().remove(acteur);
            iServiceFilm.updateFilm(film); // Update the film
        }
        iServiceActeur.deleteActeur(id);
        return "redirect:/acteur/all";

    }
}
