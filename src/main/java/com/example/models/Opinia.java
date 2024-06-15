package com.example.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "opinia")
public class Opinia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String opis;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "klient_id", nullable = false)
    private Klient klient;

}
