package it.uniroma3.siwfood.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer quantita;
    private String unitaMisura;
    @ManyToOne(cascade = CascadeType.ALL)
    private Ricetta ricetta;
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
    public Integer getQuantita() {
        return quantita;
    }
    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }
    public String getUnitaMisura() {
        return unitaMisura;
    }
    public void setUnitaMisura(String unitaMisuta) {
        this.unitaMisura = unitaMisuta;
    }
    public Ricetta getRicetta() {
        return ricetta;
    }
    public void setRicetta(Ricetta ricetta) {
        this.ricetta = ricetta;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((ricetta == null) ? 0 : ricetta.hashCode());
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
        Ingrediente other = (Ingrediente) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (ricetta == null) {
            if (other.ricetta != null)
                return false;
        } else if (!ricetta.equals(other.ricetta))
            return false;
        return true;
    }
}
