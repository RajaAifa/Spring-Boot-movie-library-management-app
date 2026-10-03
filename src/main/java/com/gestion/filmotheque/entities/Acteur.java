package com.gestion.filmotheque.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Acteur {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;

    @ManyToMany(mappedBy = "acteurs")
    private List<Film> films;

}