/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.search;

import com.basaccount.model.comptabilite.Compte;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@XmlRootElement
public class EcritureSearch extends BaseElement implements Serializable,Comparable<EcritureSearch>{

    @Predicate(label = "Compte de:",type = Compte.class,target = "many-to-one")
    private Compte source ;
    
    @Predicate(label = "Compte à:",type = Compte.class,target = "many-to-one")
    private Compte cible ;
    
    @Predicate(label = "Date de:",type = Date.class,target = "date")
    private Date debut ;
    
    @Predicate(label = "Date à:",type = Date.class,target = "date")
    private Date fin ;

    /**
     * 
     * @param source
     * @param cible
     * @param debut
     * @param fin 
     */
    public EcritureSearch(Compte source, Compte cible, Date debut, Date fin) {
        this.source = source;
        this.cible = cible;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * 
     * @param source
     * @param cible
     * @param debut
     * @param fin
     * @param id
     * @param designation
     * @param moduleName 
     */
    public EcritureSearch(Compte source, Compte cible, Date debut, Date fin, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.source = source;
        this.cible = cible;
        this.debut = debut;
        this.fin = fin;
    }

    public EcritureSearch() {
    }

    public Compte getSource() {
        return source;
    }

    public void setSource(Compte source) {
        this.source = source;
    }

    public Compte getCible() {
        return cible;
    }

    public void setCible(Compte cible) {
        this.cible = cible;
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

    @Override
    public String getEditTitle() {
        return "Critères de recherche"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Critères de recherche"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "EcritureSearch{" + "source=" + source + ", cible=" + cible + ", debut=" + debut + ", fin=" + fin + '}';
    }

       
    
    
    @Override
    public int compareTo(EcritureSearch o) {
         //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
