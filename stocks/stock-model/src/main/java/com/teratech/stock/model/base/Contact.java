/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_CONTACT")
public class Contact extends BaseElement implements Serializable,Comparable<Contact>{

    @Predicate(label = "Nom",search = true)
    private String nom ;
    
    @Predicate(label = "Civilité" ,search = true,type = Civilite.class,target = "many-to-one")
    @ManyToOne
    @JoinColumn(name = "CIV_ID")
    private Civilite civilite ;
    
    @Predicate(label = "Poste Occupé",search = true)
    private String poste ;
    
    @Predicate(label = "Courriel",search = true)
    private String courriel;
    
    @Predicate(label = "Téléphone",search = true)
    private String tel ;
    
    @Predicate(label = "Mobile",search = true)
    private String mobile;
    
    @Predicate(label = "Notes",target = "textarea",group = true,groupName = "group1",groupLabel = "Notes interne")
    private String note ;

    public Contact() {
    }

    /**
     * 
     * @param nom
     * @param civilite
     * @param poste
     * @param courriel
     * @param tel
     * @param mobile
     * @param note
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Contact(String nom, Civilite civilite, String poste, String courriel, String tel, String mobile, String note, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.nom = nom;
        this.civilite = civilite;
        this.poste = poste;
        this.courriel = courriel;
        this.tel = tel;
        this.mobile = mobile;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
     @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Contacts"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Contact"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCompareid() {
        return id; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return nom; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(Contact o) {
        //To change body of generated methods, choose Tools | Templates.
        return courriel.compareTo(o.courriel);
    }
    
}
