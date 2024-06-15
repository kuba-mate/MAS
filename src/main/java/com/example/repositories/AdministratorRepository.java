package com.example.repositories;

import com.example.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator getAdministratorById(Long id);
}
