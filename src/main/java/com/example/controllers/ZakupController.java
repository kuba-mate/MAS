package com.example.controllers;

import com.example.models.*;
import com.example.services.ZakupService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
public class ZakupController {

    @Autowired
    private ZakupService zakupService;

    @PostMapping("/submitOrder")
    public String submitOrder(
            @RequestParam("butIds") List<Long> butIds,
            @RequestParam("supplierName") String supplierName,
            @RequestParam("street") String street,
            @RequestParam("houseNumber") String houseNumber,
            @RequestParam("city") String city,
            @RequestParam("postalCode") String postalCode,
            @RequestParam("deliveryType") String deliveryType,
            @RequestParam("payNow") boolean payNow,
            HttpServletRequest request) {

        zakupService.submitOrder(butIds, supplierName, street, houseNumber, city, postalCode, deliveryType, payNow, request);

        return "redirect:/";
    }

    @GetMapping("/zakup")
    public String checkout(Model model, HttpServletRequest request) {
        final Klient klient = (Klient) request.getSession().getAttribute("user");
        final List<But> buts = klient.getKoszyk();
        int totalPrice = buts.stream().mapToInt(But::getCena).sum();
        LocalDate deliveryDate = LocalDate.now().plusDays(3);
        String formattedDeliveryDate = deliveryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        model.addAttribute("buts", buts);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("deliveryDate", formattedDeliveryDate);

        return "zakup";
    }

}
