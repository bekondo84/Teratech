/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.impl;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.jpa.impl.GenericEntityManagerImpl;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 * DAO Generic pour la couche DAO
 * @author BEKONDO Kangue Dieuendort <bekondo_dieu@yahoo.fr>
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractGenericDAO<T , PK extends Serializable> 
               extends GenericEntityManagerImpl<T>
               implements GenericDAO<T, PK>{

    /**
     * Setter Manager 
     * To overrride if necessary
     * @param manager 
     */
    public void setManager(EntityManager manager) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
