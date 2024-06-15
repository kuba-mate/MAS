package com.example.repositories;

import com.example.models.Codzienne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodzienneRepository extends JpaRepository<Codzienne, Long> {
    Codzienne findCodzienneById(Long id);
}
