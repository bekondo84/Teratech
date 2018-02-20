/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.banques;

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
@Table(name = "T_CBANCAIRE")
public class CompteBancaire extends BaseElement implements Serializable,Comparable<CompteBancaire>{

    @Predicate(label = "Numéro de compte",optional = false,unique = true,search =true )
    private String numero ;
    
    @Predicate(label = "Banque",optional = false,unique = true,type = Banque.class,target = "many-to-one",search = true)
    @ManyToOne
    @JoinColumn(name = "CB_ID")
    private Banque banque;

    public CompteBancaire() {
    }

    /**
     * 
     * @param numero
     * @param banque
     * @param id
     * @param designation
     * @param moduleName 
     */
    public CompteBancaire(String numero, Banque banque, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.numero = numero;
        this.banque = banque;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    @Override
    public String getDesignation() {
        return numero; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Comptes bancaires"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Compte bancaire"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(CompteBancaire o) {
        //To change body of generated methods, choose Tools | Templates.
        return numero.compareTo(o.numero);
    }
    
}
