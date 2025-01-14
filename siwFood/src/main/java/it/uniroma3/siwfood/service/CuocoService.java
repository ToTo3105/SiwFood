package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.repository.CuocoRepository;

@Service
public class CuocoService {

    @Autowired
    private CuocoRepository cuocoRepository;

    public Iterable<Cuoco> findAll() {
        return cuocoRepository.findAll();
    }

    public Cuoco findById(Long id) {
        return cuocoRepository.findById(id).get();
    }
}
