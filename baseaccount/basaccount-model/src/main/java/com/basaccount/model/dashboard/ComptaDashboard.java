/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.dashboard;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;

/**
 *
 * @author Commercial_2
 */
public class ComptaDashboard extends BaseElement implements Serializable,Comparable<ComptaDashboard>{

    @Predicate(label = "DEBIT" ,type = Double.class)
    private double debit = 350000;
    
    @Predicate(label = "CREDIT" ,type = Double.class)
    private double credit = 245000;

    /**
     * 
     */
    public ComptaDashboard() {
    }

    /**
     * 
     * @param id
     * @param designation
     * @param moduleName 
     */
    public ComptaDashboard(long id, String designation, String moduleName) {
        super(id, designation, moduleName);
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String getDesignation() {
        this.designation = "Comptabilité";
        return "Comptabilité"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        this.editTitle = "Tableau de bord";
        return "Tableau de bord"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(ComptaDashboard o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
