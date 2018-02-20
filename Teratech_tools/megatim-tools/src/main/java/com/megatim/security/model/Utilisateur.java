/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.megatim.security.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author divers
 */
@Entity
@Table(name="UTILISATEUR")
public class Utilisateur implements Serializable ,Comparable<Utilisateur>{
    @Id
    @Column(name="LOGIN")
    private String login;
    
    @Column(name="NOM")
    private String nom;
    
    @Column(name="PRENOM")
    private String prenom;
    
    @Column(name="PASSWORD")
    private String password;
    
    @ManyToOne
    /*@JoinColumn(name="PROFIL_NAME")*/
    private Profil profil;
    
     
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public int compareTo(Utilisateur o) {
        return login.compareTo(o.login); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.login != null ? this.login.hashCode() : 0);
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
        final Utilisateur other = (Utilisateur) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return login+"-"+nom;
    }
    
}
