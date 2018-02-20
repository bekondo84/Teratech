/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.comptabilite;

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
@Table(name = "T_SANALYTIQUE")
public class SectionAnalytique extends BaseElement implements Serializable,Comparable<SectionAnalytique>{

    @Predicate(label = "Section Analytique",type = CompteAnalytique.class,target = "many-to-one",search = true,sequence = 1,colsequence = 1)
    @ManyToOne
    @JoinColumn(name = "CA_ID")
    private CompteAnalytique compte ;    
    
    @Predicate(label = "Valeur",type = Double.class,optional = false,search = true,sequence = 2,colsequence = 3)
    private Double quantite ;
    
    @Predicate(label = "Clé de repartition" ,target = "combobox",values = "Pourcentage;Equilibre;Montant",search = true,sequence = 3,colsequence = 2)
    private String type ="0";
    
    public SectionAnalytique() {
    }

    /**
     * 
     * @param compte
     * @param quantite
     * @param id
     * @param designation
     * @param moduleName 
     */
    public SectionAnalytique(CompteAnalytique compte, Double quantite, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.compte = compte;
        this.quantite = quantite;
    }
    
    /**
     * 
     * @param section 
     */
    public SectionAnalytique(SectionAnalytique section) {
        super(section.id, section.designation, section.moduleName);
        this.compte = new CompteAnalytique(section.compte);
        this.type = section.getType();
        this.quantite = section.quantite;
    }

    public CompteAnalytique getCompte() {
        return compte;
    }

    public void setCompte(CompteAnalytique compte) {
        this.compte = compte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return super.getListTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Section Analytique"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(SectionAnalytique o) {
        //To change body of generated methods, choose Tools | Templates.
        return compte.compareTo(o.compte);
    }
    
}
