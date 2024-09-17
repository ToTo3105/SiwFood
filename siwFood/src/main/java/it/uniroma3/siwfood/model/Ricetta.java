package it.uniroma3.siwfood.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Ricetta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descrizione;
    /* 
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Immagine> immagini;
    */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ricetta")
    private Set<Ingrediente> ingredienti;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cuoco cuoco;
    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Ingrediente> getIngredienti() {
        return ingredienti;
    }
    public void setIngredienti(Set<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public Cuoco getCuoco() {
        return cuoco;
    }
    public void setCuoco(Cuoco cuoco) {
        this.cuoco = cuoco;
    }
    /*
    public Set<Immagine> getImmagini() {
        return immagini;
    }
    public void setImmagini(Set<Immagine> immagini) {
        this.immagini = immagini;
    }*/
    public Tipologia getTipologia() {
        return tipologia;
    }
    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cuoco == null) ? 0 : cuoco.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ricetta other = (Ricetta) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cuoco == null) {
            if (other.cuoco != null)
                return false;
        } else if (!cuoco.equals(other.cuoco))
            return false;
        return true;
    }
}
