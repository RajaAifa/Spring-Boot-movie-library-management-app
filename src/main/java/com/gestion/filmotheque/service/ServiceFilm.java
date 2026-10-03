package com.gestion.filmotheque.service;

import java.util.List;

import com.gestion.filmotheque.entities.Categorie;
import org.springframework.stereotype.Service;
import com.gestion.filmotheque.entities.Film;
import com.gestion.filmotheque.repository.FilmRepository;

@Service
public class ServiceFilm implements IServiceFilm {

    public ServiceFilm(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    FilmRepository filmRepository;

    @Override
    public Film createFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public Film findFilmById(int id) {
        return filmRepository.findById(id).get();
    }

    @Override
    public List<Film> findFilmByTitre(String titre) {
        return filmRepository.findByTitreContaining(titre);
    }

    @Override
    public List<Film> findFilmByCategorie(Categorie categorie) {
        return filmRepository.findByCategorie(categorie);
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film updateFilm(Film f) {
        return filmRepository.save(f);
    }

    @Override
    public void deleteFilm(int id) {
        filmRepository.deleteById(id);
    }
}
