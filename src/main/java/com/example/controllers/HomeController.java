package com.example.controllers;

import com.example.models.Klient;
import com.example.models.Logowanie;
import com.example.services.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String showMainSite(){
        return "index";
    }

    @GetMapping("/login")
    public String logIn(Model model) {
        model.addAttribute("logowanie", new Logowanie());
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute Logowanie logowanie) {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("klient", new Klient());
        return "register";
    }

    @PostMapping("/register")
    public String registerAndSaveNewPersonToDatabase(@ModelAttribute Klient klient) {
        homeService.addNewPersonToDatabase(klient);
        return "redirect:/";
    }

}
