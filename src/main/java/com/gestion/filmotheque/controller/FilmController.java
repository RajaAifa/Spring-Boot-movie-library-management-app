package com.gestion.filmotheque.controller;

import com.gestion.filmotheque.entities.Film;
import com.gestion.filmotheque.entities.Acteur;
import com.gestion.filmotheque.service.IServiceActeur;
import com.gestion.filmotheque.service.IServiceCategorie;
import com.gestion.filmotheque.service.IServiceFilm;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.Year;
import java.util.List;

@Controller
@RequestMapping("/film/")
public class FilmController {

    public FilmController(IServiceFilm iServiceFilm, IServiceCategorie iServiceCategorie, IServiceActeur iServiceActeur) {
        this.iServiceFilm = iServiceFilm;
        this.iServiceCategorie = iServiceCategorie;
        this.iServiceActeur = iServiceActeur;
    }

    IServiceFilm iServiceFilm;
    IServiceCategorie iServiceCategorie;
    IServiceActeur iServiceActeur;
    private String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\photos";

    @GetMapping("all")
    public String listeFilms(Model model) {
        model.addAttribute("films", iServiceFilm.findAllFilms());
        model.addAttribute("categories", iServiceCategorie.findAllCategories());
        return "afficheFilm";
    }

    @PostMapping("search")
    public String SearchFilm(String titre,Model model) {
        model.addAttribute("films",iServiceFilm.findFilmByTitre(titre));
        model.addAttribute("categories", iServiceCategorie.findAllCategories());
        return "afficheFilm";
    }

    @PostMapping("cat")
    public String FilterByCategorie(String idcat,Model model) {
        int idc = Integer.parseInt(idcat);
        if (idc == 0) return "redirect:/film/all";
        model.addAttribute("films",iServiceFilm.findFilmByCategorie(iServiceCategorie.findCategorieById(idc)));
        model.addAttribute("categories", iServiceCategorie.findAllCategories());
        model.addAttribute("idcat", idc);
        return "afficheFilm";
    }

    @GetMapping("new")
    public String afficheNewForm (Model model) {

        model.addAttribute("categories", iServiceCategorie.findAllCategories());
        model.addAttribute("acteurs", iServiceActeur.findAllActeurs());
        model.addAttribute("currentYear", Year.now().getValue()); // Ajouter l’année actuelle
        return "ajoutFilm";

    }

    @PostMapping("add")
    public String add(Film f, @RequestParam("file") MultipartFile multipartFile, Model model) {
       try {
           String fileName = multipartFile.getOriginalFilename();
           Path chemin = Paths.get(uploadDirectory, fileName);
           try{
               Files.write(chemin, multipartFile.getBytes());
           }catch (IOException e) {
               System.out.println(e.getMessage());
               return "redirect:/categorie/all";
           }
           f.setPhoto(fileName);
           iServiceFilm.createFilm(f);
           return "redirect:/film/all";
       }
       catch (DataIntegrityViolationException e){
           model.addAttribute("error","Ce titre existe déjà !");
           return "redirect:/film/ajout";
       }
    }

    @GetMapping("/details/{id}")
    public String afficherDetails(@PathVariable int id, Model model) {
        Film film = iServiceFilm.findFilmById(id);
        model.addAttribute("film", film);
        return "detailsFilm";
    }


    @PostMapping("modifier")
    public String modifier(Film f, @RequestParam("file") MultipartFile multipartFile) {
        Film filmExistant = iServiceFilm.findFilmById(f.getId());

        // Vérifier si un nouveau fichier a été uploadé
        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            Path chemin = Paths.get(uploadDirectory, fileName);
            try {
                Files.write(chemin, multipartFile.getBytes());
                f.setPhoto(fileName); // Mise à jour de la photo
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return "redirect:/acteur/all";
            }
        } else {
            // Conserver l'ancienne image si aucune nouvelle image n'est uploadée
            f.setPhoto(filmExistant.getPhoto());
        }

        iServiceFilm.updateFilm(f);
        return "redirect:/film/all";
    }


    @GetMapping("update/{id}")
    public String afficheUpdateForm (Model model, @PathVariable int id) {
        Film film = iServiceFilm.findFilmById(id);

        List<Integer> selectedActorIds = film.getActeurs().stream()
                .map(Acteur::getId)
                .toList();

        model.addAttribute("film", film);
        model.addAttribute("categories", iServiceCategorie.findAllCategories());
        model.addAttribute("selectedActorIds", selectedActorIds);
        model.addAttribute("acteurs", iServiceActeur.findAllActeurs());
        model.addAttribute("currentYear", Year.now().getValue()); // Ajouter l’année actuelle
        return "updateFilm";

    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable int id) {

        iServiceFilm.deleteFilm(id);
        return "redirect:/film/all";

    }
}
