package com.example.services;

import com.example.models.But;
import com.example.models.Klient;
import com.example.repositories.ButRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KoszykService {

    @Autowired
    private ButRepository butRepository;

    public But getButById(Long id){
        return butRepository.getButById(id);
    }

    public void deleteFromKoszyk(Klient klient, Long productId){
        klient.getKoszyk().removeIf(item -> item.getId().equals(productId));
    }

}
