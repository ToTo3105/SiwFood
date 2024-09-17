package it.uniroma3.siwfood.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cuoco extends Persona{

    /*
    @OneToOne(cascade = CascadeType.ALL)
    private Immagine immagine;*/
    @OneToMany(mappedBy = "cuoco")
    private Set<Ricetta> ricette;
    public Set<Ricetta> getRicette() {
        return ricette;
    }
    public void setRicette(Set<Ricetta> ricette) {
        this.ricette = ricette;
    }
    /*
     * public Immagine getImmagine() {
        return immagine;
    }
    public void setImmagine(Immagine immagine) {
        this.immagine = immagine;
    }
     */
}
