package com.example.repositories;

import com.example.models.Klient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlientRepository extends JpaRepository<Klient, Long> {
    Klient getKlientById(Long id);
}
