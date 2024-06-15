package com.example.controllers;

import com.example.models.*;
import com.example.services.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/oferta")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping()
    public String getOferta(Model model){
        List<But> buts = ofertaService.findAllByZamowienieIsNull();
        model.addAttribute("buts", buts);
        return "oferta";
    }

    @GetMapping("/{category}")
    public String getOfertaFilters(@PathVariable String category, Model model){
        List<But> result = ofertaService.getFiltersList(category);
        model.addAttribute("buts", result);
        return "oferta";
    }

    @GetMapping("/produkt/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Object but = ofertaService.checkBut(id);
        model.addAttribute("but", but);
        return "product-details";
    }

}
