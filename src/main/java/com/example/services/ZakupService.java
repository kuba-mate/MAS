package com.example.services;

import com.example.models.*;
import com.example.repositories.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class ZakupService {

    @Autowired
    private ZamowienieRepository zamowienieRepository;
    @Autowired
    private ButRepository butRepository;
    @Autowired
    private DostawaRepository dostawaRepository;
    @Autowired
    private DostawcaRepository dostawcaRepository;

    public Dostawa createDelivery(String supplierName, String street, String houseNumber, String city, String postalCode, String deliveryType) {
        Dostawa dostawa = new Dostawa();
        dostawa.setAdres(street);
        dostawa.setAdres(street + " " + houseNumber + ", " + postalCode + " " + city);
        dostawa.setTypDostawy(deliveryType.equals("paczkomat") ? TypDostawy.PACZKOMAT : TypDostawy.KURIER);
        dostawa.setDostawca(dostawa.findSupplier(supplierName));
        dostawaRepository.save(dostawa);
        return dostawa;
    }

    public void setAsociation(){
        List<Dostawca> listDostawca = dostawcaRepository.findAll();
        for(Dostawca dostawca : listDostawca){
            Dostawa.assingSupplier(dostawca.getNazwaDostawcy(), dostawca);
        }
    }

    public void submitOrder(List<Long> butIds, String supplierName, String street, String houseNumber, String city,
                            String postalCode, String deliveryType, boolean payNow, HttpServletRequest request) {
        setAsociation();
        final Klient klient = (Klient) request.getSession().getAttribute("user");
        Zamowienie zamowienie = new Zamowienie();
        List<But> buty = butRepository.findAllById(butIds);
        zamowienie.setBut(buty);
        zamowienie.setCena(buty.stream().mapToInt(But::getCena).sum());
        zamowienie.setCzy_oplacone(payNow);
        zamowienie.setData_zamowienia(LocalDate.now());
        zamowienie.setMax_data_zamowienia(LocalDate.now().plusDays(2));
        zamowienie.setStatus(payNow ? StatusZamowienia.OPLACONE : StatusZamowienia.ZLOZONE);
        zamowienie.setDostawa(createDelivery(supplierName, street, houseNumber, city, postalCode, deliveryType));
        zamowienie.setKlient(klient);

        zamowienieRepository.save(zamowienie);

        for (But but : buty) {
            but.setZamowienie(zamowienie);
            butRepository.save(but);
        }
        klient.setKoszyk(new ArrayList<>());
    }

}
