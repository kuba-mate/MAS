package com.example.repositories;

import com.example.models.Codzienne_Sportowe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Codzienne_SportoweRepository extends JpaRepository<Codzienne_Sportowe, Long> {
    Codzienne_Sportowe findCodzienne_SportoweById(Long id);
}
