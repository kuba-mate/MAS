package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "zamowienie")
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data_zamowienia;
    private LocalDate max_data_zamowienia;
    private boolean czy_oplacone;
    private StatusZamowienia status;
    private Integer cena;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "klient_id", nullable = false)
    private Klient klient;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "dostawa_id", nullable = false)
    private Dostawa dostawa;

    @OneToMany(mappedBy = "zamowienie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<But> but = new ArrayList<>();

}
