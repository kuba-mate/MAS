package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Logowanie")
public class Logowanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String haslo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_osoba", referencedColumnName = "id")
    private Osoba osoba;


}
