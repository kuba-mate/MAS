package com.example.repositories;

import com.example.models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
     List<Zamowienie> getZamowieniesByKlient_Id(Long id);
     Zamowienie getZamowieniesById(Long id);
}
