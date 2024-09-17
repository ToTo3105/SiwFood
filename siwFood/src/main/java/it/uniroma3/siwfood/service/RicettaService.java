package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.model.Tipologia;
import it.uniroma3.siwfood.repository.RicettaRepository;

@Service
public class RicettaService {


    @Autowired
    private RicettaRepository ricettaRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Iterable<Ricetta> findByTipologia(Tipologia tipologia){
        return ricettaRepository.findByTipologia(tipologia);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ricetta save(Ricetta ricetta) {
        return ricettaRepository.save(ricetta);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ricetta findById(Long idRicetta) {
        return ricettaRepository.findById(idRicetta).get();
    }
}
