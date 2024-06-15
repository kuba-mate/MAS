package com.example.services;

import com.example.models.*;
import com.example.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    private LogowanieRepository logowanieRepository;
    @Autowired
    private KlientRepository klientRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private ZamowienieRepository zamowienieRepository;

    public ResponseEntity<Boolean> validateLogin(String login, String haslo, HttpServletRequest request) {
        final Logowanie logowanie = logowanieRepository.findByLoginAndHaslo(login,haslo);
        if(logowanie == null)
            return ResponseEntity.ok(false);

        Klient user = klientRepository.getKlientById(logowanie.getOsoba().getId());
        Administrator admin = administratorRepository.getAdministratorById(logowanie.getOsoba().getId());
        if (user == null) {
            request.getSession().setAttribute("user", admin);
            request.getSession().setAttribute("role", "admin");
        } else {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", "user");
        }
        return ResponseEntity.ok(true);
    }

    public void createAdmin(Administrator administrator){
        administrator.setLogowanie(administrator.getLogowanie());
        administrator.getLogowanie().setOsoba(administrator);
        administratorRepository.save(administrator);
    }

    public Boolean checkLogin(String login) {
        return logowanieRepository.existsByLogin(login);
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void checkZamowieniaStatus() {
        List<Zamowienie> zamowienia = zamowienieRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (Zamowienie zamowienie : zamowienia) {
            if (!zamowienie.isCzy_oplacone() && zamowienie.getMax_data_zamowienia().isBefore(currentDate)) {
                zamowienie.setStatus(StatusZamowienia.ANULOWANE);
                zamowienieRepository.save(zamowienie);
            } else if(zamowienie.isCzy_oplacone() && zamowienie.getDostawa().getDataDostawy().isBefore(currentDate)){
                zamowienie.setStatus(StatusZamowienia.ZAKONCZONE);
                zamowienieRepository.save(zamowienie);
            }
        }
    }



}
