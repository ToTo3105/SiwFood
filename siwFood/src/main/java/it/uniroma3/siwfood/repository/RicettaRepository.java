package it.uniroma3.siwfood.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.model.Tipologia;

@Repository
public interface RicettaRepository extends CrudRepository<Ricetta, Long>{
    public Iterable<Ricetta> findByTipologia(Tipologia tipologia);
}
