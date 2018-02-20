/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.comptabilite;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_EXERCMBLE")
public class ExerciceComptable extends BaseElement implements Serializable,Comparable<ExerciceComptable>{

    @Predicate(label = "CODE",optional = false,updatable = false,search = true,colsequence = 1)
    @Column(unique = true,nullable = false)
    private String code ;
    
    @Predicate(label = "EXERCICE OUVERT",search = true,colsequence = 2,type = Boolean.class)
    private Boolean  ouvert = false ;
    
    @Predicate(label = "DATE DE DEBUT",type = Date.class,target = "date",optional = false,updatable = false,search = true,colsequence = 3)
    @Temporal(TemporalType.DATE)
    private Date debut ;
    
    @Predicate(label = "DATE DE FIN",type = Date.class,target = "date",optional = false,updatable = false,search = true,colsequence = 4)
    @Temporal(TemporalType.DATE)
    private Date fin ;
    
    private boolean active = false;

    /**
     * 
     * @param code
     * @param debut
     * @param fin 
     */
    public ExerciceComptable(String code, Date debut, Date fin) {
        this.code = code;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * 
     * @param code
     * @param debut
     * @param fin
     * @param id
     * @param designation
     * @param moduleName 
     */
    public ExerciceComptable(String code, Date debut, Date fin, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.debut = debut;
        this.fin = fin;
    }

    public ExerciceComptable() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    @Override
    public String getDesignation() {
        //To change body of generated methods, choose Tools | Templates.
        return code;
    }

    @Override
    public String getListTitle() {
        //To change body of generated methods, choose Tools | Templates.
        return "EXERCICES COMPTABLE";
    }

    @Override
    public String getEditTitle() {
        //To change body of generated methods, choose Tools | Templates.
        return "EXERCICE COMPTABLE";
    }
    
     @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int compareTo(ExerciceComptable o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return "ExerciceComptable{" + "code=" + code + ", ouvert=" + ouvert + ", debut=" + debut + ", fin=" + fin + ", active=" + active + '}';
    }
    
}
