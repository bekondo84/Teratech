/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.ExerciceComptable;
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
public class ClotureExerciceComptable extends BaseElement implements Serializable,Comparable<ClotureExerciceComptable>{

    @Predicate(label = "Exercice Comptable",optional = false,type = ExerciceComptable.class,target = "many-to-one")
    @ManyToOne
    private ExerciceComptable exercice ;

    /**
     * 
     * @param exercice 
     */
    public ClotureExerciceComptable(ExerciceComptable exercice) {
        this.exercice = exercice;
    }

    public ClotureExerciceComptable() {
    }
    

    /**
     * 
     * @param exercice
     * @param id
     * @param designation
     * @param moduleName 
     */
    public ClotureExerciceComptable(ExerciceComptable exercice, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.exercice = exercice;
    }

    
    public ExerciceComptable getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceComptable exercice) {
        this.exercice = exercice;
    }
    
    @Override
    public String getModuleName() {
         //To change body of generated methods, choose Tools | Templates.
        return "baseaccount";
    }
    
    
    @Override
    public int compareTo(ClotureExerciceComptable o) {
        return exercice.compareTo(o.exercice); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return super.getListTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "CLOTURE EXERCICE COMPTABLE"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "ClotureExerciceComptable{" + "exercice=" + exercice + '}';
    }
    
    
}
