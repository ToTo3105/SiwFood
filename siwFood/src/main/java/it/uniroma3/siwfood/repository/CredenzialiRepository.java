package it.uniroma3.siwfood.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siwfood.model.Credenziali;
import it.uniroma3.siwfood.model.Cuoco;

@Repository
public interface CredenzialiRepository extends CrudRepository<Credenziali, Long>{

    public Optional<Credenziali> findByUsername(String username);

    public Optional<Credenziali> findByPersona(Cuoco cuoco);

}
