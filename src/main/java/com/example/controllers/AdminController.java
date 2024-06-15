package com.example.controllers;

import com.example.models.But;
import com.example.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public String showAdminPanel(Model model) {
        model.addAttribute("but", new But());
        return "admin";
    }

    @PostMapping("/dodaj")
    public String addProduct(@ModelAttribute But but) {
        adminService.save(but);
        return "redirect:/admin";
    }

    @PostMapping("/usun")
    public String deleteProduct(@RequestParam Long id) {
        adminService.deleteById(id);
        return "redirect:/admin";
    }
}
