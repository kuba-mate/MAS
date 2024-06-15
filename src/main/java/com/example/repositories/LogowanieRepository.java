package com.example.repositories;

import com.example.models.Logowanie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogowanieRepository extends JpaRepository<Logowanie, Long> {
    Logowanie findByLoginAndHaslo(String login, String haslo);
    boolean existsByLogin(String login);
}
