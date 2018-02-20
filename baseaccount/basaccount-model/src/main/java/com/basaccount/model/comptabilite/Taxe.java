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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_TAXE")
public class Taxe extends BaseElement implements Serializable,Comparable<Taxe>{

    @Predicate(label = "Code taxe",unique = true,optional = false,search = true)
    private String code ;
    
    @Predicate(label = "Sens",target = "combobox",values = "Déductible;Collectée",search = false)    
    private String sens ="0";
    
    @Predicate(label = "Intitulé" ,optional = false,search = true)
    private String label ;
    
    @Predicate(label = "Portée de la taxe",target = "combobox",values = "Ventes;Achats;Aucun",search = false)
    private String porte = "0";
    
    @Predicate(label = "Calcul de la taxe",target = "combobox",values = "Fixé;Pourcentage du prix;Pourcentage du prix taxes incluses",search = false)
    private String calculTaxe = "0" ;
    
    @Predicate(label = "Montant" ,optional = false,type = Double.class,search = false)
    private Double montant = 0.0;
    
    @ManyToOne
    @JoinColumn(name = "SOC_ID")
    @Predicate(label = "Socièté",updatable = false,type = Societe.class,target = "many-to-one",search = true)
    private Societe societe ;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "taxe")
    private List<Compte> comptes = new ArrayList<Compte>();
    
    @Predicate(label = "Bloc-notes",group = true,groupLabel = "Bloc-notes",groupName = "group2",target = "textarea",search = false)
    private String note ;

    /**
     * 
     */
    public Taxe() {
    }

    /**
     * 
     * @param code
     * @param label
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Taxe(String code, String label, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.label = label;
    }

    
    public Taxe(Taxe taxe) {
        super(taxe.id, taxe.designation, taxe.moduleName);
        this.code = taxe.code;
        this.label = taxe.label;
        this.porte = taxe.getPorte();
        this.montant = taxe.getMontant();
        this.note = taxe.note;
        this.calculTaxe =taxe.calculTaxe;
        this.societe = taxe.getSociete();
        
    }

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens = sens;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCalculTaxe() {
        return calculTaxe;
    }

    public void setCalculTaxe(String calculTaxe) {
        this.calculTaxe = calculTaxe;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        return "Plan Taxes"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Taxe"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(Taxe o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
