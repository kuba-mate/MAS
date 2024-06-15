package com.example.services;

import com.example.models.*;
import com.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfertaService {

    @Autowired
    private ButRepository butRepository;
    @Autowired
    private CodzienneRepository codzienneRepository;
    @Autowired
    private SportoweRepository sportoweRepository;
    @Autowired
    private EleganckieRepository eleganckieRepository;
    @Autowired
    private Codzienne_SportoweRepository codzienne_sportoweRepository;
    @Autowired
    private ApiService apiService;
    public List<But> getFiltersList(String category){
        List<But> buts = butRepository.findAllByZamowienieIsNull();
        List<But> result = new ArrayList<>();
        if(category.equals("wszystkie")){
            return buts;
        } else {
            for (But but : buts) {
                Object object = checkBut(but.getId());
                if (object instanceof Codzienne_Sportowe && (category.equals("daily") || category.equals("sport"))) {
                    result.add(but);
                } else if (object instanceof Sportowe && category.equals("sport")) {
                    result.add(but);
                } else if (object instanceof Codzienne && category.equals("daily")) {
                    result.add(but);
                } else if (object instanceof Eleganckie && category.equals("eleganckie")) {
                    result.add(but);
                }
            }
        }
        return result;
    }

    public Object checkBut(Long id){
        List<But> buts = butRepository.findAll();
        But but = buts.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        if(sportoweRepository.findSportoweById(id) != null)
            if(codzienne_sportoweRepository.findCodzienne_SportoweById(id) != null)
                return codzienne_sportoweRepository.findCodzienne_SportoweById(id);
            else
                return sportoweRepository.findSportoweById(id);
        if(codzienneRepository.findCodzienneById(id) != null)
            return codzienneRepository.findCodzienneById(id);
        if(eleganckieRepository.findEleganckieById(id) != null)
            return eleganckieRepository.findEleganckieById(id);
        return but;
    }

    public List<But> findAllByZamowienieIsNull(){
        return butRepository.findAllByZamowienieIsNull();
    }


}
