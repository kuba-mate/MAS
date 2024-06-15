package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "klient")
public class Klient extends Osoba {

    @CreationTimestamp
    private LocalDate dataRejestracji;

    @Transient
    private List<But> koszyk = new ArrayList<>();

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Opinia> opinie = new ArrayList<>();

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Zamowienie> zamowienie = new ArrayList<>();

}
