package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrator")
public class Administrator extends Osoba{

    private Integer doswiadczenie;

}
