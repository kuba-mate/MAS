package com.example.services;

import com.example.models.But;
import com.example.repositories.ButRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ButRepository butRepository;

    public void save(But but){
        butRepository.save(but);
    }

    public void deleteById(Long id){
        butRepository.deleteById(id);
    }

}
