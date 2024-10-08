package com.example.controllers;

import com.example.models.Administrator;
import com.example.services.ApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/checkLogin")
    public ResponseEntity<Boolean> checkLogin(@RequestParam String login) {
        return ResponseEntity.ok(apiService.checkLogin(login));
    }

    @PostMapping("/validateLogin")
    public ResponseEntity<Boolean> validateLogin(@RequestParam String login, @RequestParam String haslo, HttpServletRequest request) {
        return apiService.validateLogin(login, haslo, request);
    }

    @PostMapping("/createAdmin")
    public void createAdmin(@RequestBody Administrator administrator){
        apiService.createAdmin(administrator);
    }

}
