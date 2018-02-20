/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megatim.security.model;

import com.megatim.security.enumerations.EnumStatutAutorisation;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author divers
 */
@Entity
@Table(name="AUTORISATION")
public class Autorisation implements Serializable,Comparable<Autorisation>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="NOM_AUTORISATION")
    private String nomAutorisation;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @Column(name="STATUT")
    private EnumStatutAutorisation statut;

    public Autorisation() {
    }
    
    public Autorisation(String nomAutorisation, String description, EnumStatutAutorisation statut) {
        this.nomAutorisation = nomAutorisation;
        this.description = description;
        this.statut = statut;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomAutorisation() {
        return nomAutorisation;
    }

    public void setNomAutorisation(String nomAutorisation) {
        this.nomAutorisation = nomAutorisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumStatutAutorisation getStatut() {
        return statut;
    }

    public void setStatut(EnumStatutAutorisation statut) {
        this.statut = statut;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.nomAutorisation != null ? this.nomAutorisation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autorisation other = (Autorisation) obj;
        if ((this.nomAutorisation == null) ? (other.nomAutorisation != null) : !this.nomAutorisation.equals(other.nomAutorisation)) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return id + " - " + nomAutorisation ;
    }
    
    public int compareTo(Autorisation o) {
        return nomAutorisation.compareTo(o.getNomAutorisation());         
    }   
}