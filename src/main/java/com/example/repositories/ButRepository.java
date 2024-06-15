package com.example.repositories;

import com.example.models.But;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ButRepository extends JpaRepository<But,Long> {
    But getButById(Long id);
    List<But> findAllByZamowienieIsNull();
}
