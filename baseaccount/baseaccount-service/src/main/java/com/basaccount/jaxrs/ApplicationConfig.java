/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.jaxrs;

import com.basaccount.jaxrs.impl.dashboard.ComptaDashboardRSImpl;
import com.basaccount.jaxrs.impl.search.EcritureSearchRSImpl;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Commercial_2
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.basaccount.jaxrs.impl.banques.BanqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.banques.CompteBancaireRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.CompteAnalytiqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.CompteRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.ExerciceComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.JournalComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.NiveauAnalyseRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.SectionAnalytiqueRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.comptabilite.TaxeRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.dashboard.ComptaDashboardRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.ClotureExerciceComptableRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.ClotureJournalRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureAnalytiqueRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.operations.EcritureTierRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.JournalSaisieRSImpl.class);  
        resources.add(com.basaccount.jaxrs.impl.operations.PieceComptableRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.search.EcritureSearchRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.CiviliteRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.ConditionPaiementRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.ContactRSImpl.class);
        resources.add(com.basaccount.jaxrs.impl.tiers.TierRSImpl.class);
    }
    
}
