package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "osoba")
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;
    private String email;
    private String numer_telefonu;

    @OneToOne(mappedBy = "osoba", cascade = CascadeType.ALL)
    private Logowanie logowanie;

}
