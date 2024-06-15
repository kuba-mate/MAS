package com.example.controllers;

import com.example.models.Klient;
import com.example.services.KoszykService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koszyk")
public class KoszykController {

    @Autowired
    private KoszykService koszykService;

    @GetMapping()
    public String viewCart(HttpServletRequest request, Model model) {
        final Klient klient = (Klient) request.getSession().getAttribute("user");
        model.addAttribute("buts", klient.getKoszyk());
        return "koszyk";
    }

    @GetMapping("/dodaj/{productId}")
    public String viewCart(@PathVariable Long productId, HttpServletRequest request) {
        final Klient klient = (Klient) request.getSession().getAttribute("user");
        klient.getKoszyk().add(koszykService.getButById(productId));
        return "redirect:/koszyk";
    }


    @GetMapping("/usun/{productId}")
    public String usunZKoszyka(@PathVariable Long productId, HttpServletRequest request) {
        final Klient klient = (Klient) request.getSession().getAttribute("user");
        koszykService.deleteFromKoszyk(klient, productId);
        return "redirect:/koszyk";
    }

}

