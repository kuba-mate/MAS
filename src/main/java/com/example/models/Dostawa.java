package com.example.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "dostawa")
public class Dostawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private static Map<String, Dostawca> dostawcy = new HashMap<>();
    private LocalDate dataDostawy;
    private TypDostawy typDostawy;
    private String adres;

    @OneToMany(mappedBy = "dostawa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zamowienie> zamowienie = new ArrayList<>();

    @NonNull
    @ManyToOne
    @JoinColumn(name = "dostawca_id", nullable = false)
    private Dostawca dostawca;

    public static void assingSupplier(String nazwaDostawcy, Dostawca dostawca) {
        if (!dostawcy.containsKey(nazwaDostawcy)) {
            dostawcy.put(nazwaDostawcy, dostawca);
        }
    }

    public Dostawca findSupplier(String nazwaDostawcy) {
        if (!dostawcy.containsKey(nazwaDostawcy)) {
            try {
                throw new Exception("Supplier not found: " + nazwaDostawcy);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dostawcy.get(nazwaDostawcy);
    }

}
