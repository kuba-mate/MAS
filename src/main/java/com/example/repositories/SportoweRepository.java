package com.example.repositories;

import com.example.models.Sportowe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportoweRepository extends JpaRepository<Sportowe, Long> {
    Sportowe findSportoweById(Long id);
}
