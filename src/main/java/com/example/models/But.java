package com.example.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "but")
@AllArgsConstructor
@NoArgsConstructor
public class But {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String marka;
    private String material;
    private String kolor;
    private String imageUrl;
    private Integer cena;
    private Integer rozmiar;

    @ManyToOne
    @JoinColumn(name = "zamowienie_id")
    private Zamowienie zamowienie;

    @Override
    public String toString() {
        return
                "id=" + getId() + '\n' +
                        "nazwa='" + getNazwa() + '\n' +
                        "marka='" + getMarka() + '\n' +
                        "material='" + getMaterial() + '\n' +
                        "kolor='" + getKolor() + '\n' +
                        "cena=" + getCena() + '\n'+
                        "rozmiar=" + getRozmiar();
    }

}
