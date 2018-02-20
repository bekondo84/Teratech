
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megatim.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.megatim.model.test.MenuComponent;

/**
 *
 * @author divers
 */
@Entity
@Table(name="PROFIL")

public class Profil implements Serializable ,Comparable<Profil>{
    
    @Id
    @Column(name="NOM")
    private String nom;
    
    @Column(name="DESCRIPTION")
    private String description;
    
    @Column(name="IS_ADMIN")
    private Boolean isAdmin = false;
    
    @OneToMany (fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinTable(name="PROFIL_AUTORISATION",
    joinColumns=@JoinColumn(name="PROFIL_NOM"),
    inverseJoinColumns=@JoinColumn(name="AUTORISATIONS_ID"))
    private List<Autorisation> autorisations = new ArrayList<Autorisation>();
    
    @Transient
    private List<MenuComponent> menus = new ArrayList<MenuComponent>();
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Autorisation> getAutorisations() {
        return autorisations;
    }

    public void setAutorisations(List<Autorisation> autorisations) {
        this.autorisations = autorisations;
    }

    public List<MenuComponent> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuComponent> menus) {
        this.menus = menus;
    }

    public Boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.nom != null ? this.nom.hashCode() : 0);
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
        final Profil other = (Profil) obj;
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        return true;
    }
    
    public int compareTo(Profil o) {
        return nom.compareTo(o.nom); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return nom + " - " + description ;
    }
    

}