package com.example.services;

import com.example.models.Klient;
import com.example.repositories.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private KlientRepository klientRepository;
    public void addNewPersonToDatabase(Klient klient) {
        klient.setLogowanie(klient.getLogowanie());
        klient.getLogowanie().setOsoba(klient);
        klientRepository.save(klient);
    }

}
