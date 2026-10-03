package com.gestion.filmotheque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String titre;
    private String description;
    private int anneeparution;
    private String photo;
    @ManyToOne
    private Categorie categorie;
    @ManyToMany
    private List<Acteur> acteurs;

    /*public String getTitre() {
        return titre;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAnneeparution() {
        return anneeparution;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnneeparution(int anneeparution) {
        this.anneeparution = anneeparution;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
     */
}