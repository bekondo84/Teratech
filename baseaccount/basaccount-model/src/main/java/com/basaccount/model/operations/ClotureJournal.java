/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@XmlRootElement
public class ClotureJournal extends BaseElement implements Serializable,Comparable<ClotureJournal>{

    @Predicate(label = "JOURNAL SAISIE",type = JournalSaisie.class,target = "many-to-one",optional = false)
    @ManyToOne
    private JournalSaisie journal ;
    
    @Predicate(label = "TYPE CLOTURE",target = "combobox",values = "Cloture partielle;Cloture totale")
    private String type = "0";

    public ClotureJournal(JournalSaisie journal) {
        this.journal = journal;
    }

    /**
     * 
     * @param journal
     * @param id
     * @param designation
     * @param moduleName 
     */
    public ClotureJournal(JournalSaisie journal, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.journal = journal;
    }

    public ClotureJournal() {
    }

    public JournalSaisie getJournal() {
        return journal;
    }

    public void setJournal(JournalSaisie journal) {
        this.journal = journal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
     @Override
    public String getModuleName() {
         //To change body of generated methods, choose Tools | Templates.
        return "baseaccount";
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
        return "CLOTURE JOURNAL DE SAISIE"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public int compareTo(ClotureJournal o) {
        //To change body of generated methods, choose Tools | Templates.
        return journal.compareTo(o.journal);
    }

    @Override
    public String toString() {
        return "ClotureJournal{" + "journal=" + journal + ", type=" + type + '}';
    }
    
    
}
