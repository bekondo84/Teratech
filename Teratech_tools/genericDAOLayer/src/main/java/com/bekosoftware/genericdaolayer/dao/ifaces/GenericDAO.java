/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer.dao.ifaces;

import com.bekosoftware.genericdaolayer.jpa.ifaces.GenericEntityManager;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author BEKONDO KANGUE D <bekondo_dieu@yahoo.fr>
 * Interface généric implémetant la couche DAO
 * @param <T>
 * @param <PK>
 * @param  T : Objet representant l'information à persister
 * @param  PK: Objet representant la clé primaire de l'object à persister
 */
public interface GenericDAO<T , PK extends Serializable> extends GenericEntityManager<T>{
    
    /**
     * 
     * @param manager 
     */
      public void setManager(EntityManager manager) ;
}
