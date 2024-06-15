package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dostawca")
public class Dostawca {

    @Id
    private Long id;

    private String nazwaDostawcy;
    private Integer oplataZaUslugi;
    private LocalDate doKiedy;

    @OneToMany(mappedBy = "dostawca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dostawa> dostawa = new ArrayList<>();

}
