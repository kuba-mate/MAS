package com.example.repositories;

import com.example.models.Eleganckie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleganckieRepository extends JpaRepository<Eleganckie, Long> {
    Eleganckie findEleganckieById(Long id);
}
