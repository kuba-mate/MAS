package com.example.controllers;

import com.example.models.Zamowienie;
import com.example.services.KontoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/konto")
public class KontoController {

    @Autowired
    private KontoService kontoService;

    @GetMapping()
    public String getKonto(Model model, HttpServletRequest request) {
        List<Zamowienie> zamowienia = kontoService.findZamowienia(request);
        model.addAttribute("zamowienia", zamowienia);
        return "konto";
    }

    @GetMapping("/oplac/{id}")
    public String oplacZamowienie(@PathVariable Long id){
        kontoService.oplacZamowienie(id);
        return "redirect:/konto";
    }

    @PostMapping("/dodajOpinie")
    public String addOpinion(String opis, HttpServletRequest request){
        kontoService.addOpinionToDatabase(opis, request);
        return "redirect:/konto";
    }

}
