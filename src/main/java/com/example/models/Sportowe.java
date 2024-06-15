package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sportowe")
public class Sportowe extends But{

    private String rodzaj_podeszwy;

    @Override
    public String toString() {
        return
                "id=" + getId() + '\n' +
                "nazwa='" + getNazwa() + '\n' +
                "marka='" + getMarka() + '\n' +
                "material='" + getMaterial() + '\n' +
                "kolor='" + getKolor() + '\n' +
                "cena=" + getCena() + '\n'+
                "rozmiar=" + getRozmiar() + '\n' +
                "rodzaj podeszwy=" + getRodzaj_podeszwy();
    }

}
