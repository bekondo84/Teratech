/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bekosoftware.genericdaolayer;

import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.Test;

/**
 *
 * @author MGT
 */
public class PersonDAOTest extends AbstractGenericDAO<Person, Long>{

    
    EntityManager em ;
    EntityTransaction tx = null ;

    public PersonDAOTest() {
        
    	Map<String, Map<BigDecimal, BigDecimal>> map ;
       /* EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.bekosoftware_genericDAOLayer");
        em = factory.createEntityManager();
        tx = em.getTransaction();*/
    }
   
    
    public EntityManager getEntityManager() {
       return em;
    }
    
    public List<Person> getListe(){
         
        return null ; //em.createQuery("SELECT c FROM Person c").getResultList();
    }

    public EntityTransaction getTx() {
        return tx;
    }

    public Class<Person> getManagedEntityClass() {
        return Person.class;
    }

    @Override
    public void processAfterSave(Person person) {
        System.out.println("::::::::::::::::: APRES APPEL DE SAVE  :::::::::::::::::::::");
    }

    @Override
    public void processBeforeSave(Person person) {
        System.out.println("::::::::::::::::: AVANT APPEL DE SAVE  :::::::::::::::::::::");
    }

   @Test
   public void test(){
	   Assert.assertTrue(true);
   }
    
    
}
