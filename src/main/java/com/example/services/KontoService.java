package com.example.services;

import com.example.models.Klient;
import com.example.models.Opinia;
import com.example.models.StatusZamowienia;
import com.example.models.Zamowienie;
import com.example.repositories.KlientRepository;
import com.example.repositories.ZamowienieRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontoService {

    @Autowired
    private ZamowienieRepository zamowienieRepository;
    @Autowired
    private KlientRepository klientRepository;

    public List<Zamowienie> findZamowienia(HttpServletRequest request){
        final Klient klient = (Klient)request.getSession().getAttribute("user");
        return zamowienieRepository.getZamowieniesByKlient_Id(klient.getId());
    }

    public void oplacZamowienie(Long id) {
        Zamowienie zamowienie = zamowienieRepository.getZamowieniesById(id);
        zamowienie.setCzy_oplacone(true);
        zamowienie.setStatus(StatusZamowienia.OPLACONE);
        zamowienieRepository.save(zamowienie);
    }

    public void addOpinionToDatabase(String opis, HttpServletRequest request){
        final Klient klient = (Klient) request.getSession().getAttribute("user");
        Opinia opinia = new Opinia(opis, klient);
        klient.getOpinie().add(opinia);
        klientRepository.save(klient);
    }

}
