/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.comptabilite;

import com.core.base.BaseElement;
import com.core.referentiels.Societe;
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
@Table(name = "T_JCOMPTABLE")
public class JournalComptable extends BaseElement implements Serializable,Comparable<JournalComptable>{

    @Predicate(label = "Code",unique = true,optional = false,updatable = false,search = true)
    private String code;
    
    @Predicate(label = "Type",target = "combobox",values = "Ventes;Achats;Trésorerie;Général;Situation")
    private String type ="0";
    
    @Predicate(label = "Intitulé",search = true)
    private String label ;
    
    @Predicate(label = "Saisie analytique",type = Boolean.class)
    private Boolean analytique = false;
    
    @ManyToOne
    @JoinColumn(name = "SOC_ID")
    @Predicate(label = "Socièté",updatable = false,type = Societe.class,target = "many-to-one",search = true)
    private Societe societe ;
    
    @Predicate(label = "Actif",type = Boolean.class)
    private Boolean active = true;

    /**
     * 
     */
    public JournalComptable() {
    }

    /**
     * 
     * @param code
     * @param label
     * @param id
     * @param designation
     * @param moduleName 
     */
    public JournalComptable(String code, String label, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getAnalytique() {
        return analytique;
    }

    public void setAnalytique(Boolean analytique) {
        this.analytique = analytique;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    
     @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code+" - "+label; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Journaux comptable"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Journal comptable"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(JournalComptable o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
