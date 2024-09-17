package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siwfood.model.Credenziali;
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.repository.CredenzialiRepository;

@Service
public class CredenzialiService {

    @Autowired
    private CredenzialiRepository credenzialiRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Credenziali save(Credenziali credenziali) {
        return credenzialiRepository.save(credenziali);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Credenziali findByUsername(String username) {
        return credenzialiRepository.findByUsername(username).get();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Credenziali findByPersona(Cuoco cuoco) {
        return credenzialiRepository.findByPersona(cuoco).get();
    }
}
